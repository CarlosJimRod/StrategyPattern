package com.cjimenezro.strategypattern.features.superHeroes.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroeApiService {

    @GET("heroes.json")
    suspend fun getPrincipaData():Response<List<SuperHeroePincipalDataApiModel>>
}