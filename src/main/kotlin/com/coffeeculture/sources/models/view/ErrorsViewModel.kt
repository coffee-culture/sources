package com.coffeeculture.sources.models.view

data class ErrorsViewModel(
    val errors: List<ErrorViewModel> = emptyList()
)

data class ErrorViewModel(
    val type: String,
    val message: String
)
