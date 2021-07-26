package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.PunkRepository
import org.koin.core.KoinComponent

class GetBeerList(private val punkRepository: PunkRepository) : KoinComponent {

    operator fun invoke(page: Int, perPage: Int) = punkRepository.getBeersList(page, perPage)
}