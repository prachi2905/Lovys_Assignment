package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.PunkRepository
import org.koin.core.KoinComponent

class GetSearchBeer(private val punkRepository: PunkRepository) : KoinComponent {

    operator fun invoke(beerName: String, page: Int, perPage: Int) =
        punkRepository.getSearchBeer(beerName, page, perPage)
}