package com.stambulo.themovie.di.module

import com.stambulo.themovie.MovieApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MovieApplication) {

    @Singleton
    @Provides
    fun app(): MovieApplication {
        return app
    }
}
