package com.coffeeculture.sources.transformers

import com.coffeeculture.sources.models.data.Roaster
import com.coffeeculture.sources.models.view.RoasterUrl
import com.coffeeculture.sources.models.view.RoasterUrlType
import com.coffeeculture.sources.models.view.RoasterViewModel
import com.coffeeculture.sources.models.view.Status
import javax.inject.Singleton

interface RoasterToViewModelTransformer : Transformer<Roaster, RoasterViewModel> {
    override fun transform(source: Roaster): RoasterViewModel
    override fun transform(source: Iterable<Roaster>): Iterable<RoasterViewModel>
}

@Singleton
class RoasterToViewModelTransformerImpl : RoasterToViewModelTransformer {
    override fun transform(source: Roaster): RoasterViewModel {
        return RoasterViewModel(
            id = source.id,
            name = source.name,
            urls = transformUrls(source),
            status = Status.valueOf(source.status.name),
            createdAt = source.createdAt,
            updatedAt = source.updatedAt,
            deletedAt = source.deletedAt
        )
    }

    override fun transform(source: Iterable<Roaster>): Iterable<RoasterViewModel> {
        return source.map { transform(it) }
    }

    private fun transformUrls(roaster: Roaster): List<RoasterUrl> {
        return roaster.urls.map {
            RoasterUrl(
                type = RoasterUrlType.valueOf(it.type.name), // eww...
                value = it.value
            )
        }
    }
}
