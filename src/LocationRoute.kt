
import domain.Booking
import domain.Listing
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.get

@KtorExperimentalLocationsAPI
fun Routing.userLocation() {

    get<Booking> { listing ->
        call.respondText(listing.lakeName)
    }

    get<Listing> { listing ->
        call.respondText("Listing ${listing.name}, page ${listing.page}")
    }

}