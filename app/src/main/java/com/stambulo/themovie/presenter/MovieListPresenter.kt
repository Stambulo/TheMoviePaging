package com.stambulo.themovie.presenter

import com.stambulo.themovie.view.ListView
import com.stambulo.themovie.view.RvAdapter
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MovieListPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<ListView>() {

    val rvPresenter = RvPresenter()

    class RvPresenter{
        private val data = arrayListOf(
            Data(0, "Элемент списка"), Data(1, "Элемент списка"),
            Data(2, "Элемент списка"), Data(3, "Элемент списка"),
            Data(4, "Элемент списка"), Data(5, "Элемент списка"),
            Data(6, "Элемент списка"), Data(7, "Элемент списка"))

        data class Data(
            val id: Int = 0,
            val someText: String = "Text"
        )

        fun getCount() = data.size

        fun bindView(view: RvAdapter.ViewHolder){
            val movie = data[view.pos]
            movie.id.let { view.setId(it) }
            movie.someText.let { view.setSomeText(it) }
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }
}
