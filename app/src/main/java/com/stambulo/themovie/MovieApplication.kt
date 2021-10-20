package com.stambulo.themovie

import android.app.Application

class MovieApplication: Application() {
    val INSTANCE: MovieApplication = this
    val apiHolder: ApiHolder = ApiHolder()
}
