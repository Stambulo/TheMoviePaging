package com.stambulo.themovie.viewmodel

sealed class MainIntent {
    object FetchMovies : MainIntent()
}
