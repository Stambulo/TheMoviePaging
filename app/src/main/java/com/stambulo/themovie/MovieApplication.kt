package com.stambulo.themovie

import android.app.Application
import com.stambulo.themovie.di.AppComponent
import com.stambulo.themovie.di.DaggerAppComponent
import com.stambulo.themovie.di.module.AppModule

class MovieApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: MovieApplication
    }
}
