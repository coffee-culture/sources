package com.coffeeculture.sources.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.coffeeculture.sources.common.JacksonConfig
import com.coffeeculture.sources.common.Roasters
import com.coffeeculture.sources.config.DatastoreFactory
import io.micronaut.core.io.scan.DefaultClassPathResourceLoader
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object InMemoryRoasterRepositorySpec : Spek({

    describe("InMemoryRoasterRepository") {
        val mapper = JacksonConfig.getObjectMapper()
        val resourceLoader = DefaultClassPathResourceLoader(this.javaClass.classLoader)

        val datastoreFactory = DatastoreFactory(mapper, resourceLoader)
        val repository = InMemoryRoasterRepository(datastoreFactory.getDatastore())

        describe("#find") {
            it("returns 6 roasters") {
                val result = repository.find()

                assertThat(result.size).isEqualTo(6)
            }
        }

        describe("#findById") {
            it("returns expected roaster") {
                val result = repository.findById("08fa6ef4-0c64-4635-8a9d-c4ac973c04dc")
                val expected = Roasters.littleWolfCoffee()

                assertThat(result).isEqualTo(expected)
            }

            it("returns null when roaster does not exist") {
                val result = repository.findById("invalid-id")

                assertThat(result).isNull()
            }
        }
    }
})
