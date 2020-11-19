package com.velocip.io

import com.velocip.io.domain.SwimmingEvent
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.ButtonFormMethod
import io.ktor.client.HttpClient
import java.util.*
import io.ktor.request.*

fun Routing.event(client: HttpClient) {

    get("/swimmingevent") {
        call.respond(SwimmingEvent(UUID.randomUUID().toString(), "Default event", 100))
    }

    get("/swimmingeventservice"){
        application.log.info("Consume BEGIN")

        val result = client.get<SwimmingEvent>("http://127.0.0.1:8080/swimmingevent")
        application.log.info("result: $result")
        call.respond(result)

        application.log.info("Consume END")
    }
}