package com.coffeeculture.sources

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.coffeeculture.sources")
                .mainClass(Application.javaClass)
                .start()
    }
}
