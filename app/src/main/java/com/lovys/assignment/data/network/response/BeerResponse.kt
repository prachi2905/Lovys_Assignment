package com.lovys.assignment.data.network

import com.google.gson.annotations.SerializedName

class BeerResponse(
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    @SerializedName("image_url") val imageURL: String,
    val abv: Float,
    val ibu: Float
)