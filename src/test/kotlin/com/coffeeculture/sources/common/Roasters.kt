package com.coffeeculture.sources.common

import com.coffeeculture.sources.models.data.Roaster
import com.coffeeculture.sources.models.data.RoasterUrl
import com.coffeeculture.sources.models.data.RoasterUrlName
import com.coffeeculture.sources.models.data.Status
import java.net.URL
import java.time.Instant

object Roasters {
    fun littleWolfCoffee(): Roaster {
        return Roaster(
            id = "08fa6ef4-0c64-4635-8a9d-c4ac973c04dc",
            name = "Little Wolf Coffee",
            status = Status.DISABLED,
            urls = listOf(
                RoasterUrl(
                    name = RoasterUrlName.HOME,
                    url = URL("https://littlewolf.coffee/")
                ),
                RoasterUrl(
                    name = RoasterUrlName.SCRAPE,
                    url = URL("https://littlewolf.coffee/collections/shop")
                )
            ),
            createdAt = Instant.parse("2020-04-18T13:10:53Z")
        )
    }
}
