package com.stambulo.themovie.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.stambulo.themovie.databinding.FragmentMovieListBinding
import com.stambulo.themovie.model.api.ApiHelperImpl
import com.stambulo.themovie.model.api.RetrofitBuilder
import com.stambulo.themovie.model.entity.Discover
import com.stambulo.themovie.view.MoviesAdapter
import com.stambulo.themovie.viewmodel.MainIntent
import com.stambulo.themovie.viewmodel.MainState
import com.stambulo.themovie.viewmodel.MainViewModel
import com.stambulo.themovie.viewmodel.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListFragment: Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _viewBinding: FragmentMovieListBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)
    private var adapter = MoviesAdapter(arrayListOf())

    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        setupViewModel()
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        getData()
    }

    private fun setupUI() {
        viewBinding.rvMovies.adapter = adapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchMovies)
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {
                    }

                    is MainState.Loading -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                        viewBinding.rvMovies.visibility = View.GONE
                        viewBinding.text.visibility = View.GONE
                    }

                    is MainState.Movies -> {
                        with(viewBinding) {
                            progressBar.visibility = View.GONE
                            text.visibility = View.GONE
                            it.movies.body()?.let { movieList -> renderList(movieList)}
                        }
                    }

                    is MainState.Error -> {
                        viewBinding.progressBar.visibility = View.GONE
                        viewBinding.rvMovies.visibility = View.GONE
                        viewBinding.text.visibility = View.GONE
                        Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(movies: Discover) {
        viewBinding.rvMovies.visibility = View.VISIBLE
        movies.let { adapter.addData(it) }
        adapter.notifyDataSetChanged()
    }
}
