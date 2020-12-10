import data.DataManager
import com.ktor.sample.domain.HypermediaLink
import com.ktor.sample.domain.SwimmingEvent
import com.ktor.sample.domain.SwimmingEventResponse
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*


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

        get("/{eventId}"){
            val eventId = call.parameters["eventId"]

            val event = dm.getEvent(eventId)
            val hypermediaLinks: List<HypermediaLink> = listOf(
                HypermediaLink(
                    "http://localhost:8080/swimevent/{$eventId}/details",
                    "details",
                    "GET"
                )
            )

            val eventSwimResponse = SwimmingEventResponse(event, hypermediaLinks)
            call.respond(eventSwimResponse)
        }

        get("/{eventId}/details"){
            val eventId = call.parameters["eventId"]
            val event = dm.getEvent(eventId)
            call.respondText("Details for ${event?.name} event")
        }

    }
}
