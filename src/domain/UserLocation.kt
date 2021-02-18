package domain

import io.ktor.locations.*


@KtorExperimentalLocationsAPI
@Location("/booking/{bookingName}/uid{userId}/lakename{lakeName}")
data class Booking(val bookingName: String, val userId: Int, val lakeName: String)

// booking/morningswim/uid/1234/lakename/deepswim



@KtorExperimentalLocationsAPI
@Location("/list/{name}/page/{page}")
data class Listing(val name: String, val page: Int)