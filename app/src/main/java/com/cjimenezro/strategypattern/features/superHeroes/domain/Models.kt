package com.cjimenezro.strategypattern.features.superHeroes.domain

data class SuperHeroe (
    val id: String,
    val name: String,
    val imageUrl: MutableList<String>,
    val stats: MutableList<String>
)