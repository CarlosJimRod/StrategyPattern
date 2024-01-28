package com.cjimenezro.strategypattern.features.superHeroes.data.strategy

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.features.superHeroes.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class RemoteStrategy(private val remoteDataSource: SuperHeroesRemoteDataSource) : DataStrategy {

    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>> {
        return remoteDataSource.getSuperHeroe()
    }
}