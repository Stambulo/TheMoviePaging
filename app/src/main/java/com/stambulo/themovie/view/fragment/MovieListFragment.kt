package com.stambulo.themovie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.databinding.FragmentMovieListBinding
import com.stambulo.themovie.model.api.ApiHolder
import com.stambulo.themovie.model.api.ApiService
import com.stambulo.themovie.model.api.RepositoryImpl
import com.stambulo.themovie.model.entity.VideoItem
import com.stambulo.themovie.view.MovieLoadStateAdapter
import com.stambulo.themovie.view.MoviesAdapter
import com.stambulo.themovie.view.OnListItemClickListener
import com.stambulo.themovie.view.navigation.screens.Screens
import com.stambulo.themovie.viewmodel.VideosViewModel
import com.stambulo.themovie.viewmodel.VideosViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MovieListFragment : Fragment() {

    @Inject lateinit var router: Router
    private val api: ApiService = ApiHolder.apiService
    private val repository = RepositoryImpl(api)
    private lateinit var viewModel: VideosViewModel
    private var _viewBinding: FragmentMovieListBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MoviesAdapter(onListItemClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MovieApplication.instance.appComponent.inject(this)
        _viewBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = VideosViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[VideosViewModel::class.java]
        setupUI()
        loadData()
    }

    private fun setupUI() {
        viewBinding.rvMovies.adapter = adapter
        viewBinding.rvMovies.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { adapter.retry() },
            footer = MovieLoadStateAdapter { adapter.retry() },
        )

        viewBinding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
        loadStateRefresh()
        viewBinding.retry.setOnClickListener { adapter.refresh() }
    }

    private fun loadStateRefresh() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadStates ->
                val refresh = loadStates.refresh
                viewBinding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
                viewBinding.retry.isVisible = refresh is LoadState.Error
            }
        }
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: VideoItem) {
                router.navigateTo(Screens.DetailsScreen(data.id))
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    companion object { fun newInstance() = MovieListFragment() }
}
