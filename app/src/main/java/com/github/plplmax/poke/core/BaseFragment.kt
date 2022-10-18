package com.github.plplmax.poke.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.plplmax.poke.core.navigation.Navigation
import com.github.plplmax.poke.main.ui.MainActivity

typealias Inflate<Binding> = (LayoutInflater, ViewGroup?, Boolean) -> Binding

abstract class BaseFragment<Binding : ViewBinding>(
    private val inflate: Inflate<Binding>
) : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!
    protected lateinit var nav: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nav = (requireActivity() as MainActivity).navComponent.navigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
