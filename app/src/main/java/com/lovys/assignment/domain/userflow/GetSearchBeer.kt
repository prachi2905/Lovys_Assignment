package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.BeerRepository
import org.koin.core.KoinComponent

class GetSearchBeer(private val beerRepository: BeerRepository) : KoinComponent {

    operator fun invoke(beerName: String, page: Int, perPage: Int) =
        beerRepository.getSearchBeer(beerName, page, perPage)
}