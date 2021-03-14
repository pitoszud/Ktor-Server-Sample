
import domain.SwimUser
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.get

@KtorExperimentalLocationsAPI
fun Routing.userLocation() {

    get<EventLocation>{
        call.respondText("Location nane=${it.locationname}")
    }

    get<Event.SwimEvent>{
        call.respondText("$it")
    }

    get<Event.EventList>{
        call.respondText("$it")
    }

    // http://localhost:8080/event/organised/list?sortby=eventname&asc=1
    // EventList(event=Event(category=organised), sortby=eventname, asc=1)

}


@KtorExperimentalLocationsAPI
@Location("/eventlocation/{locationname}")
class EventLocation(val locationname: String)


@KtorExperimentalLocationsAPI
@Location("/event/{category}")
data class Event(val category: String){
    @Location("/{eventname}")
    data class SwimEvent(val event: Event, val eventname: String)

    @Location("/list")
    data class EventList(val event: Event, val sortby: String, val asc: Int)
}