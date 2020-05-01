package com.coffeeculture.sources.controllers

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotNull
import com.coffeeculture.sources.common.testdata.RoasterViewModels
import com.coffeeculture.sources.common.testdata.Roasters
import com.coffeeculture.sources.exceptions.NotFoundException
import com.coffeeculture.sources.services.RoasterService
import com.coffeeculture.sources.transformers.RoasterToViewModelTransformerImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.micronaut.http.HttpStatus
import kotlin.test.assertFailsWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class RoastersControllerSpec : Spek({

    describe("RoastersController") {
        val littleWolfCoffeeId = "08fa6ef4-0c64-4635-8a9d-c4ac973c04dc"
        val referrer = "test-suite"

        // setup service mock
        val service: RoasterService = mock()
        whenever(service.getAll()).thenReturn(Roasters.all())
        whenever(service.getById(littleWolfCoffeeId)).thenReturn(Roasters.littleWolfCoffee())

        val controller = RoastersController(service = service, transformer = RoasterToViewModelTransformerImpl())

        describe("#index") {
            it("returns all roasters") {
                val rsp = controller.index(referrer = referrer)

                assertThat(rsp.status).isEqualTo(HttpStatus.OK)
                assertThat(rsp.body()).isNotNull().isNotEmpty()
                assertThat(rsp.body()?.size).isEqualTo(2)
                assertThat(rsp.body()).isEqualTo(RoasterViewModels.all())
            }
        }

        describe("#show") {
            it("returns expected roaster") {
                val rsp = controller.show(referrer = referrer, id = littleWolfCoffeeId)

                assertThat(rsp.status).isEqualTo(HttpStatus.OK)
                assertThat(rsp.body).isNotNull()
                assertThat(rsp.body()).isEqualTo(RoasterViewModels.littleWolfCoffee())
            }

            it("throws NotFoundException when roaster does not exist") {
                val invalidId = "invalid-id-111"

                assertFailsWith(
                    exceptionClass = NotFoundException::class,
                    message = "no roaster found for ID $invalidId",
                    block = { controller.show(referrer = referrer, id = invalidId) }
                )
            }
        }
    }
})
