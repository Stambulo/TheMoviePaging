package com.stambulo.themovie.di.module

import com.stambulo.themovie.MovieApplication
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: MovieApplication) {

    @Provides
    fun app(): MovieApplication {
        return app
    }

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

}
