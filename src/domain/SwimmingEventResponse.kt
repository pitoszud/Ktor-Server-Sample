package com.ktor.sample.domain

data class SwimmingEventResponse(
    val swimmingEvent: SwimmingEvent?,
    val links: List<HypermediaLink>
)