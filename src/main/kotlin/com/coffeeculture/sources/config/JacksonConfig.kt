package com.coffeeculture.sources.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class JacksonConfig {

    /*
        This ObjectMapper configuration is duplicated into the test source set. The configurations must be kept
        consistent or tests may not provide adequate coverage. Ideally, this bean would be defined in a common project
        that shares such configurations across all of the source sets.
     */
    @Singleton
    @Bean
    fun getObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}
