package com.cjimenezro.strategypattern.features.superHeroes.data.remote

import com.google.gson.annotations.SerializedName

class SuperHeroePincipalDataApiModel (
    @SerializedName("id") val id:Int,
    @SerializedName("name")val name:String,
    @SerializedName("images") val urlImages: ImagesUrl,
    @SerializedName("powerstats") val stats: Powerstats
)

class ImagesUrl(
    @SerializedName("sm") val imageUrl1:String,
    @SerializedName("xs") val imageUrl2:String,
    @SerializedName("md") val imageUrl3:String,
    @SerializedName("lg") val imageUrl4:String,
)

class Powerstats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("combat") val combat:String
)