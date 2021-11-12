package com.stambulo.themovie.di

import com.stambulo.themovie.di.module.AppModule
import com.stambulo.themovie.di.module.CiceroneModule
import com.stambulo.themovie.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
//        ApiModule::class,
//        RepoModule::class,
        AppModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
