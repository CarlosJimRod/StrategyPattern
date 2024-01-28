package com.cjimenezro.strategypattern.features.superHeroes.data.strategy

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.features.superHeroes.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.strategypattern.features.superHeroes.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class RemoteLocalStrategy(
    private val localDataSource: SuperHeroeLocalDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource,
) : DataStrategy {
    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>> {
        return remoteDataSource.getSuperHeroe().map { list->
            localDataSource.deletePrincipalData()
            list.map {superHeroe->
                localDataSource.saveSuperHeroe(superHeroe)
            }
            list
        }
    }
}