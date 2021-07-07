package com.coffeeculture.sources.exceptions.handlers

import com.coffeeculture.sources.exceptions.NotFoundException
import com.coffeeculture.sources.models.view.ErrorViewModel
import com.coffeeculture.sources.models.view.ErrorsViewModel
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [NotFoundException::class, ExceptionHandler::class])
class NotFoundExceptionHandler : ExceptionHandler<NotFoundException, HttpResponse<Any>> {

    override fun handle(request: HttpRequest<Any>, exception: NotFoundException): HttpResponse<Any> {
        return HttpResponse.notFound(
            ErrorsViewModel(
                errors = listOf(
                    ErrorViewModel(
                        type = HttpStatus.NOT_FOUND.toString(),
                        message = exception.message ?: HttpStatus.NOT_FOUND.reason
                    )
                )
            )
        )
    }
}
