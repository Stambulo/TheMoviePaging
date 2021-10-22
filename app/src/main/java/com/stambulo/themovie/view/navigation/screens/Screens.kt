package com.stambulo.themovie.view.navigation.screens

import com.stambulo.themovie.view.fragment.MovieListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MovieListScreen: SupportAppScreen() {
        override fun getFragment() = MovieListFragment.newInstance()
    }
}
