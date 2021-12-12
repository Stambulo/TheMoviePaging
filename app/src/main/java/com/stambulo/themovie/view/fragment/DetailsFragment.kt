package com.stambulo.themovie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.stambulo.themovie.BackButtonListener
import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.databinding.FragmentDetailsBinding
import com.stambulo.themovie.model.api.ApiHolder
import com.stambulo.themovie.model.api.ApiService
import com.stambulo.themovie.model.api.RepositoryImpl
import com.stambulo.themovie.model.entity.Video
import com.stambulo.themovie.model.state.DetailsState
import com.stambulo.themovie.view.navigation.screens.Screens
import com.stambulo.themovie.viewmodel.DetailsViewModel
import com.stambulo.themovie.viewmodel.DetailsViewModelFactory
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsFragment : Fragment(), BackButtonListener {

    @Inject lateinit var router: Router
    private val api: ApiService = ApiHolder.apiService
    private val repository = RepositoryImpl(api)
    private lateinit var viewModel: DetailsViewModel
    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)
    private var movieId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MovieApplication.instance.appComponent.inject(this)
        _viewBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = DetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DetailsViewModel::class.java]
        setupObserver()
        viewModel.getDetails(movieId)
    }

    private fun setupObserver() {
        viewModel.data.observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result) {
                    is DetailsState.Success<Video> -> { showSuccess(result.data) }
                    is DetailsState.Error -> { showError(result) }
                    is DetailsState.Loading -> { showLoading() }
                }
            }
        }
    }

    private fun setupUI(data: Video) {
        viewBinding.apply {
            Glide.with(viewBinding.imgPoster.context)
                .load("https://image.tmdb.org/t/p/w500/" + data.poster_path)
                .into(viewBinding.imgPoster)
            Glide.with(viewBinding.imgBadge.context)
                .load("https://image.tmdb.org/t/p/w500/" + data.backdrop_path)
                .into(viewBinding.imgBadge)
            title.text = data.title
            overview.text = data.overview
        }
    }

    private fun showSuccess(data: Video?) {
        if (data != null) {
            viewBinding.apply {
                progressDetails.visibility = View.GONE
                this.data.visibility = View.VISIBLE
                dataAbsent.visibility = View.GONE
            }
            setupUI(data)
        } else {
            viewBinding.apply {
                progressDetails.visibility = View.GONE
                this.data.visibility = View.GONE
                dataAbsent.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(result: DetailsState.Error) {
        viewBinding.apply {
            progressDetails.visibility = View.GONE
            this.data.visibility = View.GONE
            dataAbsent.visibility = View.VISIBLE
            Snackbar.make(
                viewBinding.root,
                result.error.localizedMessage ?: "",
                Snackbar.LENGTH_LONG,
            ).show()
        }
    }

    private fun showLoading() {
        viewBinding.apply {
            progressDetails.visibility = View.VISIBLE
            dataAbsent.visibility = View.GONE
        }
    }

    override fun backPressed(): Boolean {
        router.navigateTo(Screens.MovieListScreen())
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    companion object {
        fun newInstance(id: Int) = DetailsFragment().apply {
            movieId = id
        }
    }
}
