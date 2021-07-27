package com.lovys.assignment.data.mapper

import com.lovys.assignment.data.network.BeerResponse
import com.lovys.assignment.domain.localdb.Beer


class BeerMapperService : BaseMapperRepository<BeerResponse, Beer> {

    override fun transform(type: BeerResponse): Beer =
        Beer(
            type.id,
            type.name,
            type.description,
            type.tagline,
            type.imageURL,
            type.abv,
            type.ibu
        )

    override fun transformToRepository(type: Beer): BeerResponse =
        BeerResponse(
            type.id,
            type.name,
            type.description,
            type.tagline,
            type.imageURL,
            type.abv,
            type.ibu
        )
}