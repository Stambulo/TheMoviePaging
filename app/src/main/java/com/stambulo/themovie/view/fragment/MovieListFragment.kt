package com.stambulo.themovie.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.databinding.FragmentMovieListBinding
import com.stambulo.themovie.presenter.MovieListPresenter
import com.stambulo.themovie.view.ListView
import com.stambulo.themovie.view.RvAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_movie_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MovieListFragment: MvpAppCompatFragment(), ListView {
    private var _viewBinding: FragmentMovieListBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)

    companion object{
        fun newInstance() = MovieListFragment()
    }

    val presenter: MovieListPresenter by moxyPresenter {
        MovieListPresenter(AndroidSchedulers.mainThread()).apply {
            MovieApplication.instance.appComponent.inject(this)
        }
    }

    var adapter: RvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _viewBinding?.root
    }

    override fun init(){
        rv_movie.layoutManager = GridLayoutManager(context, 2)
        adapter = RvAdapter(presenter.rvPresenter).apply {
            MovieApplication.instance.appComponent.inject(this)
        }
        rv_movie.adapter = adapter
    }
}
