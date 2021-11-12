package com.stambulo.themovie.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.stambulo.themovie.databinding.FragmentMovieListBinding
import com.stambulo.themovie.m2.ApiHelperImpl
import com.stambulo.themovie.m2.MainViewModel2
import com.stambulo.themovie.m2.RetrofitBuilder
import com.stambulo.themovie.m2.ViewModelFactory2
import com.stambulo.themovie.viewmodel.MainState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListFragment: Fragment() {

    private lateinit var mainViewModel: MainViewModel2
    private var _viewBinding: FragmentMovieListBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)

    companion object{
        fun newInstance() = MovieListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        setupViewModel()
        return _viewBinding?.root
    }

//    private fun setupViewModel() {
//        mainViewModel = ViewModelProviders.of(
//            this, ViewModelFactory()).get(MainViewModel::class.java)
//    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this, ViewModelFactory2(ApiHelperImpl(RetrofitBuilder.apiService)))
            .get(MainViewModel2::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(">>>", "onViewCreated")
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {Log.i(">>>", "idle")}

                    is MainState.Loading -> {Log.i(">>>", "loading")}

                    is MainState.Movies -> {Log.i(">>>", "Movies")}

                    is MainState.Error -> {Log.i(">>>", "error")}
                }
            }
        }
    }
}
