package com.cjimenezro.strategypattern.features.superHeroes.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroesApiClient {

    private val urlApi=" https://dam.sitehub.es/api-curso/superheroes/"
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
    private val getRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    var retrofit=getRetrofit.create(SuperHeroeApiService::class.java)
}