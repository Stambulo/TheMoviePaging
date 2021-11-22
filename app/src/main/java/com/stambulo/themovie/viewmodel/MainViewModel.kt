package com.stambulo.themovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stambulo.themovie.model.repository.MoviesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel (private val repository: MoviesRepository) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchMovies -> fetchMovies()
                }
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Movies(repository.getMovies(55))
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}
