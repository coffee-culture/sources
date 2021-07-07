package com.coffeeculture.sources.services

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotNull
import com.coffeeculture.sources.common.testdata.Roasters
import com.coffeeculture.sources.repositories.RoasterRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RoasterServiceSpec : Spek({

    describe("RoasterService") {
        val littleWolfCoffeeId = "08fa6ef4-0c64-4635-8a9d-c4ac973c04dc"

        // setup repository mock
        val repository: RoasterRepository = mock()
        whenever(repository.find()).thenReturn(Roasters.all())
        whenever(repository.findById(littleWolfCoffeeId)).thenReturn(Roasters.littleWolfCoffee())

        val service = RoasterServiceImpl(repository)

        describe("#getAll") {
            it("retrieves all roasters") {
                val result = service.getAll()

                assertThat(result).isNotEmpty()
                assertThat(result.size).isEqualTo(2)
                assertThat(result).isEqualTo(Roasters.all())
            }
        }

        describe("#getById") {
            it("retrieves roaster by ID") {
                val result = service.getById(littleWolfCoffeeId)

                assertThat(result).isNotNull()
                assertThat(result).isEqualTo(Roasters.littleWolfCoffee())
            }
        }
    }
})
