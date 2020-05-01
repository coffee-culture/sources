package com.coffeeculture.sources.controllers.api

import com.coffeeculture.sources.common.testdata.Roasters
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItems
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class RoastersApiSpec : Spek({

    describe("RoastersApi") {
        val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        RestAssured.baseURI = embeddedServer.url.toString()
        RestAssured.port = embeddedServer.port

        describe("#index") {
            it("returns expected roasters") {
                // This test could be improved by validating the response using a JsonSchema.
                Given {
                    header("X-Referrer", "test-suite")
                } When {
                    get("/roasters")
                } Then {
                    statusCode(HttpStatus.OK.code)
                    body(
                        "id", hasItems(
                            "08fa6ef4-0c64-4635-8a9d-c4ac973c04dc",
                            "2b8df1e8-6cf5-4cdf-9f84-a819bf23301d",
                            "f25a57d2-093c-43f0-b4d8-a2da75ef6ad0",
                            "b1c73bd3-72ba-4b4b-aef2-68bd9149ee84",
                            "56d2464e-f784-44ca-ad0b-eec19659a32a",
                            "bd4d5dac-ca1b-498c-84c1-f6a24bf36571"
                        )
                    )
                    body(
                        "name", hasItems(
                            "Little Wolf Coffee",
                            "Flatlands Coffee",
                            "Counter Culture Coffee",
                            "Verve Coffee Roasters",
                            "Scratch Living Coffee",
                            "Sweet Bloom Coffee Roasters"
                        )
                    )
                }
            }

            it("requires X-Referrer header") {
                When {
                    get("/roasters")
                } Then {
                    statusCode(HttpStatus.BAD_REQUEST.code)
                    body("message", equalTo("Required Header [X-Referrer] not specified"))
                }
            }
        }

        describe("#show") {
            it("returns roaster by ID") {
                val littleWolfCoffee = Roasters.littleWolfCoffee()

                Given {
                    header("X-Referrer", "test-suite")
                } When {
                    get("/roasters/${littleWolfCoffee.id}")
                } Then {
                    statusCode(HttpStatus.OK.code)
                    body("id", equalTo(littleWolfCoffee.id))
                    body("name", equalTo(littleWolfCoffee.name))
                    body("urls[0].type", equalTo(littleWolfCoffee.urls[0].type.name))
                    body("urls[0].value", equalTo(littleWolfCoffee.urls[0].value.toString()))
                    body("urls[1].type", equalTo(littleWolfCoffee.urls[1].type.name))
                    body("urls[1].value", equalTo(littleWolfCoffee.urls[1].value.toString()))
                    body("status", equalTo(littleWolfCoffee.status.name))
                    body("created_at", equalTo("2020-04-18T13:10:53Z"))
                }
            }

            it("returns 404 Not Found when roaster not found by ID") {
                val invalidId = "invalid-111"

                Given {
                    header("X-Referrer", "test-suite")
                } When {
                    get("/roasters/$invalidId")
                } Then {
                    statusCode(HttpStatus.NOT_FOUND.code)
                    body("errors[0].message", equalTo("no roaster found for ID $invalidId"))
                }
            }

            it("requires X-Referrer header") {
                When {
                    get("/roasters")
                } Then {
                    statusCode(HttpStatus.BAD_REQUEST.code)
                    body("message", equalTo("Required Header [X-Referrer] not specified"))
                }
            }
        }
    }
})
