package com.stambulo.themovie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stambulo.themovie.model.repository.IMovieRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel() : ViewModel() {

    @Inject lateinit var repository: IMovieRepository

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {

        Log.i(">>>", "init")
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            Log.i(">>>", "handleIntent")
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchMovie -> fetchMovies()
                }
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            Log.i(">>>", "Fetch")
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Movies(repository.getMovies(1))
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}
