package com.coffeeculture.sources.controllers.api

import com.coffeeculture.sources.models.view.RoasterViewModel
import io.micronaut.http.HttpResponse

interface RoastersApi {
    fun index(referrer: String): HttpResponse<List<RoasterViewModel>>
    fun show(referrer: String, id: String): HttpResponse<RoasterViewModel>
}
