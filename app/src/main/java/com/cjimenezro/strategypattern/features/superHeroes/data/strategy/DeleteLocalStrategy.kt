package com.cjimenezro.strategypattern.features.superHeroes.data.strategy

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.features.superHeroes.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class DeleteLocalStrategy(private val localDataSource: SuperHeroeLocalDataSource) : DataStrategy {

    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>> {
        localDataSource.deletePrincipalData()
        return localDataSource.getSuperHeroe()
    }
}