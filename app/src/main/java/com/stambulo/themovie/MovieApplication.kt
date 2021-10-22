package com.stambulo.themovie

import android.app.Application
import com.stambulo.themovie.di.AppComponent
import com.stambulo.themovie.di.DaggerAppComponent
import com.stambulo.themovie.di.module.AppModule
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MovieApplication: Application() {
    companion object{
        lateinit var instance: MovieApplication
    }
//    val INSTANCE: MovieApplication = this
    val apiHolder: ApiHolder = ApiHolder()

    lateinit var appComponent: AppComponent
        private set

    private val cicerone: Cicerone<Router> = Cicerone.create()

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
