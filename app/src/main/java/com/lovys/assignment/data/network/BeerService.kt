package com.lovys.assignment.data.repo.service


import com.lovys.assignment.data.mapper.BeerMapperService
import com.lovys.assignment.data.network.api.BeerAPI
import com.lovys.assignment.data.repo.PunkRequestGenerator
import com.lovys.assignment.domain.localdb.Beer
import com.lovys.assignment.domain.utils.Results


class BeerService {
    private val api: PunkRequestGenerator = PunkRequestGenerator()
    private val mapper: BeerMapperService = BeerMapperService()

    fun getBeersById(id: Int): Results<List<Beer>> {
        val callResponse = api.createService(BeerAPI::class.java).getBeersById(id)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Results.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Results.Failure(Exception(response.message()))
        }
        return Results.Failure(Exception("Bad request/response"))
    }

    fun getBeersList(page: Int, perPage: Int): Results<List<Beer>> {
        val callResponse = api.createService(BeerAPI::class.java).getBeersList(page, perPage)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Results.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Results.Failure(Exception(response.message()))
        }
        return Results.Failure(Exception("Bad request/response"))
    }

    fun getSearchBeer(beerName: String, page: Int, perPage: Int): Results<List<Beer>> {
        val callResponse =
            api.createService(BeerAPI::class.java).getSearchBeer(beerName, page, perPage)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return Results.Success(response.body()?.map {
                    mapper.transform(it)
                })
            }
            return Results.Failure(Exception(response.message()))
        }
        return Results.Failure(Exception("Bad request/response"))
    }
}