package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.BeerRepository
import org.koin.core.KoinComponent

class GetBeerList(private val beerRepository: BeerRepository) : KoinComponent {

    operator fun invoke(page: Int, perPage: Int) = beerRepository.getBeersList(page, perPage)
}