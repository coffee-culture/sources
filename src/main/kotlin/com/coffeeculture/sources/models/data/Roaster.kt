package com.coffeeculture.sources.models.data

import java.net.URL
import java.time.Instant

data class Roaster(
    val id: String,
    val name: String,
    val urls: List<RoasterUrl>,
    val status: Status = Status.ENABLED,
    val createdAt: Instant,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null
)

enum class Status {
    ENABLED,
    DISABLED
}

data class RoasterUrl(
    val name: RoasterUrlName,
    val url: URL
)

enum class RoasterUrlName {
    HOME,
    SCRAPE
}
