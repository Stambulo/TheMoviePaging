package com.stambulo.themovie.di

import com.stambulo.themovie.di.module.AppModule
import com.stambulo.themovie.di.module.CiceroneModule
import com.stambulo.themovie.view.MainActivity
import com.stambulo.themovie.view.fragment.DetailsFragment
import com.stambulo.themovie.view.fragment.MovieListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
    ],
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(movieListFragment: MovieListFragment)
    fun inject(detailsFragment: DetailsFragment)
}
