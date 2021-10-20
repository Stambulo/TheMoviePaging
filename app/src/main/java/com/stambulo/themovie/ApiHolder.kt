package com.stambulo.themovie

import com.google.gson.GsonBuilder
import com.stambulo.themovie.model.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiHolder {
    private val baseUrl = "https://api.themoviedb.org/3/"
    private var dataSource: ApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()

        dataSource = retrofit.create(ApiService::class.java)
    }

    fun getApi() = dataSource
}
