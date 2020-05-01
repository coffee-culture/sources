package com.coffeeculture.sources.config

import com.coffeeculture.sources.models.data.Roaster
import com.coffeeculture.sources.repositories.InMemoryRoasterRepository
import com.coffeeculture.sources.repositories.RoasterRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceLoader
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.event.annotation.EventListener
import javax.inject.Singleton
import org.slf4j.LoggerFactory

@Factory
class DatastoreFactory(
    private val mapper: ObjectMapper,
    private val resourceLoader: ResourceLoader
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private lateinit var roasterRepository: RoasterRepository

    companion object {
        private const val DATA_FILE_URL = "classpath:data/roasters.json"
    }

    // Initialize the in memory datastore on app startup
    @EventListener
    fun init(event: ServiceStartedEvent) {
        roasterRepository = InMemoryRoasterRepository(getDatastore())
    }

    @Singleton
    @Bean
    fun roasterRepository(): RoasterRepository {
        return roasterRepository
    }

    fun getDatastore(): Map<String, Roaster> {
        val data = readDataFile(DATA_FILE_URL)
        logger.info("Initializing in memory datastore with ${data.size} roasters")

        return data.map {
            it.id to it
        }.toMap()
    }

    // There's some cleanup that could be done here, but as this is a not the long-term data store, it will work as is.
    fun readDataFile(fileName: String): List<Roaster> {
        val inputStream = resourceLoader.getResourceAsStream(fileName).orElseThrow()
        val typeReference = object : TypeReference<List<Roaster>>() {}

        return mapper.readValue(inputStream, typeReference)
    }
}
