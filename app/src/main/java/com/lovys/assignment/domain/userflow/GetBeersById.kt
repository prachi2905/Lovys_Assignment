package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.BeerRepository
import org.koin.core.KoinComponent


class GetBeersById (private val beerRepository: BeerRepository) : KoinComponent{

    operator fun invoke(id: Int) = beerRepository.getBeersById(id)
}