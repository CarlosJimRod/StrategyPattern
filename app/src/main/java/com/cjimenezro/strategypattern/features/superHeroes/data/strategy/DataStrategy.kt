package com.cjimenezro.strategypattern.features.superHeroes.data.strategy

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

interface DataStrategy {
    suspend fun getSuperHeroes():Either<ErrorApp,List<SuperHeroe>>
}