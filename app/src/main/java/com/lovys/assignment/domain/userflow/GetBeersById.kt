package com.lovys.assignment.domain.userflow

import com.lovys.assignment.data.repo.PunkRepository
import org.koin.core.KoinComponent


class GetBeersById (private val punkRepository: PunkRepository) : KoinComponent{

    operator fun invoke(id: Int) = punkRepository.getBeersById(id)
}