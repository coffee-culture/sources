package com.coffeeculture.sources.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JacksonConfig {

    /*
        This ObjectMapper configuration is duplicated from the main source set. The configurations must be kept
        consistent or tests may not provide adequate coverage. Ideally, this bean would be defined in a common project
        that shares such configurations across all of the source sets.
     */
    fun getObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}
