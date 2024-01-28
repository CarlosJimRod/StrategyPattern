package com.cjimenezro.strategypattern.features.superHeroes.domain

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp


interface SuperHeroeRepository {

    suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroe>>

}