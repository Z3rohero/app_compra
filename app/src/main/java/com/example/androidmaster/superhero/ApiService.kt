package com.example.androidmaster.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.*

interface ApiService {

    @GET("/api/b717ef89db074c0ab7d933d134aa80d3/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>




}