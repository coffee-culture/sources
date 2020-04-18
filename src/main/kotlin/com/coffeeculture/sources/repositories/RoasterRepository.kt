package com.coffeeculture.sources.repositories

import com.coffeeculture.sources.models.data.Roaster
import javax.inject.Singleton

interface RoasterRepository {
    fun find(): List<Roaster>
    fun findById(id: String): Roaster?
}

@Singleton
class InMemoryRoasterRepository(
    private val data: Map<String, Roaster>
) : RoasterRepository {

    override fun find(): List<Roaster> {
        return data.values.toList()
    }

    override fun findById(id: String): Roaster? {
        return data[id]
    }
}
