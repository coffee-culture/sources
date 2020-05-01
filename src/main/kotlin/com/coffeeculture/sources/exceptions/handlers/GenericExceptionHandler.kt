package com.coffeeculture.sources.exceptions.handlers

import com.coffeeculture.sources.models.view.ErrorViewModel
import com.coffeeculture.sources.models.view.ErrorsViewModel
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

// [GenericExceptionHandler] is a generic catchall for exceptions thrown from Controllers that do not have a specific
// handler implementation. This handler returns 500/Internal Server Error.

@Produces
@Singleton
@Requires(classes = [Exception::class, ExceptionHandler::class])
class GenericExceptionHandler : ExceptionHandler<Exception, HttpResponse<Any>> {

    override fun handle(request: HttpRequest<Any>, exception: Exception): HttpResponse<Any> {
        return HttpResponse.serverError(
            ErrorsViewModel(
                errors = listOf(
                    ErrorViewModel(
                        type = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        message = exception.message ?: HttpStatus.INTERNAL_SERVER_ERROR.reason
                    )
                )
            )
        )
    }
}
