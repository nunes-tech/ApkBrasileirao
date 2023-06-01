package com.nunes.brasileirao.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServices {
    const val TOKEN = "Bearer live_2942984482a43daadb61c0f9321772"
    private val okHttpClient = OkHttpClient().newBuilder()
        .writeTimeout(10,TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .addInterceptor( AuthInterceptor() )
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-futebol.com.br/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(IRetrofitServices::class.java)
}