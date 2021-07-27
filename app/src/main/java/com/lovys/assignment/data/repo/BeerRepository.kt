package com.lovys.assignment.data.repo


import com.lovys.assignment.domain.localdb.Beer
import com.lovys.assignment.domain.utils.Results

interface BeerRepository {

    fun getBeersById(id: Int)
            : Results<List<Beer>>

    fun getBeersList(page: Int, perPage: Int)
            : Results<List<Beer>>

    fun getSearchBeer(
        beerName: String,
        page: Int,
        perPage: Int
    ): Results<List<Beer>>
}