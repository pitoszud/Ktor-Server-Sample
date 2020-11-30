package com.velocip.io

import com.velocip.io.data.DataManager
import com.velocip.io.domain.HypermediaLink
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


fun Route.swimmingEvent(){
    val dm = DataManager()

    route("/swim"){
        get("/"){
            call.respond(dm.allEvents())
        }

        post("/{id}"){
            val id = call.parameters["id"]
            val swimmingEvent = call.receive(SwimmingEvent::class)
            dm.updateEvent(swimmingEvent)
            call.respondText { "Event updated" }
        }


        put(""){
            val swimmingEvent = call.receive(SwimmingEvent::class)
            dm.addEvent(swimmingEvent)
            call.respondText { "Event added" }
        }


        delete("/{id}"){
            val id = call.parameters["id"]

            id?.let {
                dm.removeEvent(it)
            }

            call.respondText { "Event Deleted" }

        }
    }

}


fun Route.swimEvent(){
    val dm = DataManager()
    route("swimevent"){
        get("/"){
            call.respond(dm.allEvents())
        }

        get("/{id}"){
            val eventId = call.parameters["eventId"]
            val event = dm.getEvent(eventId)
            val hypermediaLinks = listOf<HypermediaLink>(
                HypermediaLink("http://localhost:8080/swimevent/eventdetails")
            )

        }

    }
}
