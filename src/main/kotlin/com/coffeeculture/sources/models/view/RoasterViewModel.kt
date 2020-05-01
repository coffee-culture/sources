package com.coffeeculture.sources.models.view

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URL
import java.time.Instant

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class RoasterViewModel(
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
    val type: RoasterUrlType,
    val value: URL
)

enum class RoasterUrlType {
    HOME,
    SCRAPE
}
