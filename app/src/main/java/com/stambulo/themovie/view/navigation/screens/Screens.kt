package com.stambulo.themovie.view.navigation.screens

import com.stambulo.themovie.view.fragment.DetailsFragment
import com.stambulo.themovie.view.fragment.MovieListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MovieListScreen : SupportAppScreen() {
        override fun getFragment() = MovieListFragment.newInstance()
    }

    class DetailsScreen(private val id: Int) : SupportAppScreen() {
        override fun getFragment() = DetailsFragment.newInstance(id)
    }
}
