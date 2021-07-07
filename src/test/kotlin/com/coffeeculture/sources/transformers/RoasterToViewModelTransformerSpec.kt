package com.coffeeculture.sources.transformers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.coffeeculture.sources.common.testdata.RoasterViewModels
import com.coffeeculture.sources.common.testdata.Roasters
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RoasterToViewModelTransformerSpec : Spek({

    describe("RoasterToViewModelTransformer") {
        val transformer = RoasterToViewModelTransformerImpl()

        it("transforms a single roaster") {
            val result = transformer.transform(Roasters.littleWolfCoffee())

            assertThat(result).isEqualTo(RoasterViewModels.littleWolfCoffee())
        }

        it("transforms a collection of roasters") {
            val result = transformer.transform(Roasters.all())

            assertThat(result).isEqualTo(RoasterViewModels.all())
        }
    }
})
