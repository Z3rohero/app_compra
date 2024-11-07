package com.example.androidmaster.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse (
    @SerializedName("response" ) val response :String,
    @SerializedName("results")val superheroes : List<SuperheroItemResponse>
    )



data class  SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImageResponse,

    )

data class SuperheroImageResponse(@SerializedName("url") var url: String)