package com.github.plplmax.poke.detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.github.plplmax.poke.App
import com.github.plplmax.poke.R
import com.github.plplmax.poke.core.BaseFragment
import com.github.plplmax.poke.databinding.FragmentDetailBinding
import com.github.plplmax.poke.detail.ui.viewmodel.DetailViewModel
import com.github.plplmax.poke.detail.ui.viewmodel.DetailViewModelFactory
import com.github.plplmax.poke.detail.ui.viewmodel.DetailViewModelOf
import com.google.android.material.snackbar.Snackbar

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private lateinit var factory: DetailViewModelFactory
    private val vm: DetailViewModel by viewModels<DetailViewModelOf> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factory =
            (requireActivity().applicationContext as App).appComponent.detailViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        observeSuccess()
        observeFailure()
        observeLoading()
        savedInstanceState ?: run {
            val id = requireArguments().getInt(ID_KEY)
            vm.fetch(id)
        }
    }

    private fun initToolbar() {
        binding.layoutToolbar.topAppBar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
        binding.layoutToolbar.topAppBar.setNavigationOnClickListener {
            nav.popBackStack()
        }
    }

    private fun observeSuccess() {
        vm.observeSuccess(viewLifecycleOwner) { pokemon ->
            binding.pokemonName.text = pokemon.name
            binding.pokemonHeight.text = "${pokemon.height}"
            binding.pokemonWeight.text = "${pokemon.weight}"
            configureHp(pokemon.hp)
            configureAttack(pokemon.attack)
            configureDefense(pokemon.defense)
            configureSpeed(pokemon.speed)
            configureExperience(pokemon.experience)
            Glide.with(requireContext()).load(pokemon.imageUrl).into(binding.pokemonImage)
        }
    }

    private fun observeFailure() {
        vm.observeFailure(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun observeLoading() {
        vm.observeLoading(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.nestedView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.nestedView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun configureHp(hp: Int) {
        binding.hpProgress.pokemonProgress.progress = hp.toFloat()
        binding.hpProgress.pokemonProgress.labelText = "$hp/300"
        binding.hpProgress.pokemonProgress.max = 300f
        binding.hpProgress.pokemonProgress.highlightView.color =
            ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
    }

    private fun configureAttack(attack: Int) {
        binding.atkProgress.pokemonProgress.progress = attack.toFloat()
        binding.atkProgress.pokemonProgress.labelText = "$attack/300"
        binding.atkProgress.pokemonProgress.max = 300f
        binding.atkProgress.pokemonProgress.highlightView.color =
            ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
    }

    private fun configureDefense(defense: Int) {
        binding.defProgress.pokemonProgress.progress = defense.toFloat()
        binding.defProgress.pokemonProgress.labelText = "$defense/300"
        binding.defProgress.pokemonProgress.max = 300f
        binding.defProgress.pokemonProgress.highlightView.color =
            ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark)
    }

    private fun configureSpeed(speed: Int) {
        binding.spdProgress.pokemonProgress.progress = speed.toFloat()
        binding.spdProgress.pokemonProgress.labelText = "$speed/300"
        binding.spdProgress.pokemonProgress.max = 300f
        binding.spdProgress.pokemonProgress.highlightView.color =
            ContextCompat.getColor(requireContext(), android.R.color.holo_purple)
    }

    private fun configureExperience(experience: Int) {
        binding.expProgress.pokemonProgress.progress = experience.toFloat()
        binding.expProgress.pokemonProgress.labelText = "$experience/1000"
        binding.expProgress.pokemonProgress.max = 1000f
        binding.expProgress.pokemonProgress.highlightView.color =
            ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
    }

    companion object {
        private const val ID_KEY = "id_key"

        fun newInstance(id: Int): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(ID_KEY to id)
            }
        }
    }
}
