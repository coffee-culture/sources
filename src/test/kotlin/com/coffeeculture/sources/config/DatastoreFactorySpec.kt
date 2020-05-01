package com.coffeeculture.sources.config

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.coffeeculture.sources.common.JacksonConfig
import com.coffeeculture.sources.common.testdata.Roasters
import io.micronaut.core.io.scan.DefaultClassPathResourceLoader
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object DatastoreFactorySpec : Spek({
    describe("DatastoreFactory") {
        // If the InMemoryDataStore were not a temp solution, I'd turn this into an integration test and inject both
        // the ResourceLoader and ObjectMapper.
        val mapper = JacksonConfig.getObjectMapper()
        val resourceLoader = DefaultClassPathResourceLoader(this.javaClass.classLoader)
        val datastoreFactory = DatastoreFactory(mapper, resourceLoader)

        describe("#getDatastore") {
            val datastore = datastoreFactory.getDatastore()

            it("returns a collection containing 6 coffee roasters") {
                assertThat(datastore.size).isEqualTo(6)
            }

            it("deserializes roaster from data file") {
                val result = datastore["08fa6ef4-0c64-4635-8a9d-c4ac973c04dc"]
                val expected = Roasters.littleWolfCoffee()

                assertThat(result).isEqualTo(expected)
            }
        }

        describe("#readDataFile") {
            it("reads roasters from json file") {
                val roasters = datastoreFactory.readDataFile("classpath:data/roasters.json")

                assertThat(roasters.size).isEqualTo(6)
            }
        }
    }
})
