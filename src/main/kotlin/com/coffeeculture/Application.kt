package com.coffeeculture

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.coffeeculture")
                .mainClass(Application.javaClass)
                .start()
    }
}