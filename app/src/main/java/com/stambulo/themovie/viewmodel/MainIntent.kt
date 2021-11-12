package com.stambulo.themovie.viewmodel

sealed class MainIntent {
    object FetchMovie : MainIntent()
}
