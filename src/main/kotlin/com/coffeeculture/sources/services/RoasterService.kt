package com.coffeeculture.sources.services

import com.coffeeculture.sources.models.data.Roaster
import com.coffeeculture.sources.repositories.RoasterRepository
import javax.inject.Singleton

interface RoasterService {
    fun getAll(): List<Roaster>
    fun getById(id: String): Roaster?
}

@Singleton
class RoasterServiceImpl(
    private val repository: RoasterRepository
) : RoasterService {

    override fun getAll(): List<Roaster> {
        return repository.find()
    }

    override fun getById(id: String): Roaster? {
        return repository.findById(id)
    }
}
