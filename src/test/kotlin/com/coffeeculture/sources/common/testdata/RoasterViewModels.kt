package com.coffeeculture.sources.common.testdata

import com.coffeeculture.sources.models.view.RoasterUrl
import com.coffeeculture.sources.models.view.RoasterUrlType
import com.coffeeculture.sources.models.view.RoasterViewModel
import com.coffeeculture.sources.models.view.Status
import java.net.URL
import java.time.Instant

object RoasterViewModels {
    fun littleWolfCoffee(): RoasterViewModel {
        return RoasterViewModel(
            id = "08fa6ef4-0c64-4635-8a9d-c4ac973c04dc",
            name = "Little Wolf Coffee",
            status = Status.DISABLED,
            urls = listOf(
                RoasterUrl(
                    type = RoasterUrlType.HOME,
                    value = URL("https://littlewolf.coffee/")
                ),
                RoasterUrl(
                    type = RoasterUrlType.SCRAPE,
                    value = URL("https://littlewolf.coffee/collections/shop")
                )
            ),
            createdAt = Instant.parse("2020-04-18T13:10:53Z")
        )
    }

    fun flatlandsCoffee(): RoasterViewModel {
        return RoasterViewModel(
            id = "2b8df1e8-6cf5-4cdf-9f84-a819bf23301d",
            name = "Flatlands Coffee",
            status = Status.DISABLED,
            urls = listOf(
                RoasterUrl(
                    type = RoasterUrlType.HOME,
                    value = URL("https://www.flatlandscoffee.com/")
                ),
                RoasterUrl(
                    type = RoasterUrlType.SCRAPE,
                    value = URL("https://www.flatlandscoffee.com/shop")
                )
            ),
            createdAt = Instant.parse("2020-04-18T13:10:53Z")
        )
    }

    fun all(): List<RoasterViewModel> {
        return listOf(littleWolfCoffee(), flatlandsCoffee())
    }
}
