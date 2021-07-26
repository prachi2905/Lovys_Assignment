package com.lovys.assignment.data.network.api

import com.lovys.assignment.data.network.BeerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerAPI {

    @GET("v2/beers")
    fun getBeersById(
        @Query("ids") id: Int
    ): Call<List<BeerResponse>>

    @GET("v2/beers")
    fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<BeerResponse>>

    @GET("v2/beers")
    fun getSearchBeer(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<BeerResponse>>
}