package com.stambulo.themovie.presenter

import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.model.api.ApiService
import com.stambulo.themovie.model.repository.MovieRepository
import com.stambulo.themovie.view.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    private val dataSource: ApiService = MovieApplication().INSTANCE.apiHolder.getApi()
    private val api = MovieRepository(dataSource)

    fun requestMovie(id: Int) {
        api.getMovie(id).observeOn(AndroidSchedulers.mainThread()).subscribe({
            viewState.showMovie(it.title)
        }, {/*Error*/ })
    }

    fun requestMovies(page: Int) {
        api.getMovies(page).observeOn(AndroidSchedulers.mainThread())
            .subscribe({/*Success*/}, {/*Error*/ })
    }
}
