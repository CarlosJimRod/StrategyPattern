package com.cjimenezro.strategypattern.features.superHeroes.domain

import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.app.domain.left
import com.cjimenezro.strategypattern.app.domain.right


class GetSuperHeroeUseCase(
    private val superHeroeRepository: SuperHeroeRepository,
) {

    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroe>> {
        return superHeroeRepository.obratinSuperHeroe()
    }

}