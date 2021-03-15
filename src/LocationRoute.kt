
import domain.SwimUser
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.get

// https://ktor.io/docs/features-locations.html#route-classes

@KtorExperimentalLocationsAPI
fun Routing.userLocation() {

    get<EventLocation>{
        call.respondText("$it")
    }

    get<Event.SwimEvent>{
        call.respondText("$it")
    }

    get<Event.EventList>{
        call.respondText("$it")
    }
}


@KtorExperimentalLocationsAPI
@Location("/eventlocation/{locationname}/lat/{eventlat}/lon/{eventlon}")
data class EventLocation(val locationname: String, val eventlat: Double, val eventlon: Double)


@KtorExperimentalLocationsAPI
@Location("/event/{category}")
data class Event(val category: String){
    @Location("/{eventname}")
    data class SwimEvent(val event: Event, val eventname: String)

    @Location("/list")
    data class EventList(val event: Event, val sortby: String, val asc: Int)
}