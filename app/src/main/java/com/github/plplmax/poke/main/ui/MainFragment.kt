package com.github.plplmax.poke.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.plplmax.poke.App
import com.github.plplmax.poke.R
import com.github.plplmax.poke.core.BaseFragment
import com.github.plplmax.poke.databinding.FragmentMainBinding
import com.github.plplmax.poke.main.ui.viewmodel.MainViewModel
import com.github.plplmax.poke.main.ui.viewmodel.MainViewModelFactory
import com.github.plplmax.poke.main.ui.viewmodel.MainViewModelOf
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private var adapter: PokemonAdapter? = null
    private lateinit var manager: GridLayoutManager
    private lateinit var mainFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by viewModels<MainViewModelOf> { mainFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        mainFactory = appComponent.mainViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPokemonList()
        observePokemonList()
        observeLoading()
        observeError()
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }

    private fun initPokemonList() {
        manager = configuredLayoutManager()
        adapter = PokemonAdapter { id ->
            mainViewModel.clearError()
            nav.toDetailScreen(id)
        }
        binding.pokemonList.layoutManager = manager
        binding.pokemonList.adapter = adapter
        binding.pokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (needUpdate()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        mainViewModel.fetchNext()
                    }
                }
            }
        })
    }

    private fun configuredLayoutManager(): GridLayoutManager {
        return GridLayoutManager(requireActivity(), SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (adapter!!.getItemViewType(position) == PokemonAdapter.ViewType.MAIN.ordinal) {
                        1
                    } else {
                        2
                    }
                }
            }
        }
    }

    private fun observePokemonList() {
        mainViewModel.observePokemons(viewLifecycleOwner) { pokemons ->
            if (pokemons.isEmpty()) {
                hidePokemonList()
                showInformationText()
            } else {
                showPokemonList()
                hideInformationText()
            }
            adapter!!.update(pokemons)
        }
    }

    private fun observeLoading() {
        mainViewModel.observeLoading(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                hideInformationText()
                if (adapter!!.areItemsEmpty()) {
                    showLoader()
                } else {
                    adapter!!.enableLoading()
                }
            } else {
                hideLoader()
                adapter!!.disableLoading()
            }
        }
    }

    private fun observeError() {
        mainViewModel.observeError(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry) {
                        mainViewModel.fetchNext()
                    }.show()
            }
        }
    }

    private fun needUpdate(): Boolean {
        val isRemainingElementsSmall =
            manager.findFirstVisibleItemPosition() >= adapter!!.remainingElementsWithOffset()
        return !mainViewModel.errorExists() && isRemainingElementsSmall
    }

    private fun showPokemonList() {
        binding.pokemonList.visibility = View.VISIBLE
    }

    private fun hidePokemonList() {
        binding.pokemonList.visibility = View.GONE
    }

    private fun showLoader() {
        binding.progressIndicator.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.progressIndicator.visibility = View.GONE
    }

    private fun showInformationText() {
        binding.informationText.visibility = View.VISIBLE
    }

    private fun hideInformationText() {
        binding.informationText.visibility = View.GONE
    }

    private companion object {
        const val SPAN_COUNT: Int = 2
    }
}
