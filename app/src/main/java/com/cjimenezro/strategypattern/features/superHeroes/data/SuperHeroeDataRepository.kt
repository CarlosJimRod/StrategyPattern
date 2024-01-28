package com.cjimenezro.strategypattern.features.superHeroes.data

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.features.superHeroes.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.strategypattern.features.superHeroes.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.DataStrategy
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroeRepository

class SuperHeroeDataRepository (private val strategy: DataStrategy) : SuperHeroeRepository {

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroe>> {
        return strategy.getSuperHeroes()
    }
}