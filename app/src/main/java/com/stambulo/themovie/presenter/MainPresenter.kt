package com.stambulo.themovie.presenter

import com.stambulo.themovie.view.MainView
import com.stambulo.themovie.view.navigation.screens.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.MovieListScreen())
    }
}
