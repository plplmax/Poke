package com.github.plplmax.poke.core.navigation

import androidx.fragment.app.FragmentManager
import com.github.plplmax.poke.R
import com.github.plplmax.poke.detail.ui.DetailFragment
import com.github.plplmax.poke.main.ui.MainFragment

class AppNavigation(private val manager: FragmentManager) : Navigation {
    override fun toMainScreen() {
        val fragment = MainFragment()
        manager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun toDetailScreen(id: Int) {
        val fragment = DetailFragment.newInstance(id)
        manager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(DetailFragment::class.simpleName)
            .commit()
    }

    override fun popBackStack() {
        manager.popBackStack()
    }
}
