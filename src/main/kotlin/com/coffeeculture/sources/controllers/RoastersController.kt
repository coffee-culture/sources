package com.coffeeculture.sources.controllers

import com.coffeeculture.sources.controllers.api.RoastersApi
import com.coffeeculture.sources.exceptions.NotFoundException
import com.coffeeculture.sources.models.view.RoasterViewModel
import com.coffeeculture.sources.services.RoasterService
import com.coffeeculture.sources.transformers.RoasterToViewModelTransformer
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header

// Some filtering examples can be found in the JPA Data Access guide.
// https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html

@Controller("/roasters")
class RoastersController(
    private val service: RoasterService,
    private val transformer: RoasterToViewModelTransformer
) : RoastersApi {

    @Get(produces = [MediaType.APPLICATION_JSON])
    override fun index(@Header(value = "X-Referrer") referrer: String): HttpResponse<List<RoasterViewModel>> {
        val roasters = service.getAll()
        val view = transformer.transform(roasters).toList()
        return HttpResponse.ok(view)
    }

    @Get("{id}", produces = [MediaType.APPLICATION_JSON])
    override fun show(@Header(value = "X-Referrer") referrer: String, id: String): HttpResponse<RoasterViewModel> {
        val roaster = service.getById(id) ?: throw NotFoundException("no roaster found for ID $id")
        val view = transformer.transform(roaster)
        return HttpResponse.ok(view)
    }
}
