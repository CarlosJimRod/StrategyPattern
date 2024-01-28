package com.cjimenezro.strategypattern.features.superHeroes.data.remote

import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe


fun ImagesUrl.toModel():MutableList<String> = mutableListOf(this.imageUrl1,this.imageUrl2,this.imageUrl3,this.imageUrl4)

fun Powerstats.toModel():MutableList<String> = mutableListOf(this.intelligence,this.speed,this.combat)

fun SuperHeroePincipalDataApiModel.toModel(): SuperHeroe =
    SuperHeroe(
        this.id.toString(),
        this.name,
        this.urlImages.toModel(),
        this.stats.toModel()
    )