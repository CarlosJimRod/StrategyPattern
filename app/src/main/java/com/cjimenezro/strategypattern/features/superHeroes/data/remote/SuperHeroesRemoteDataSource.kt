package com.cjimenezro.strategypattern.features.superHeroes.data.remote

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.app.domain.left
import com.cjimenezro.strategypattern.app.domain.right
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe


class SuperHeroesRemoteDataSource (
    private val apiClient: SuperHeroesApiClient
) {

    suspend fun getSuperHeroe(): Either<ErrorApp, List<SuperHeroe>> {
        return try {
            apiClient.retrofit.getPrincipaData().body()!!.map {
                it.toModel()
            }.right()
        }catch (ex:Exception){
            ErrorApp.InternetError.left()
        }
    }

}