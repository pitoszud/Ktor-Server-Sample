package com.velocip.io.domain

data class SwimmingEventResponse(
    val swimmingEvent: SwimmingEvent?,
    val links: List<HypermediaLink>
)