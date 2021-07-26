package com.lovys.assignment.data.repo

import com.lovys.assignment.data.repo.service.BeerService
import com.lovys.assignment.domain.localdb.Beer
import com.lovys.assignment.domain.utils.Results

class PunkRepositoryImpl(
    private val beerService: BeerService
) : PunkRepository {


    override fun getBeersById(id: Int): Results<List<Beer>> {
        return beerService.getBeersById(id)
    }

    override fun getBeersList(page: Int, perPage: Int): Results<List<Beer>> {
        return beerService.getBeersList(page, perPage)
    }

    override fun getSearchBeer(beerName: String, page: Int, perPage: Int): Results<List<Beer>> {
        return beerService.getSearchBeer(beerName, page, perPage)
    }
}