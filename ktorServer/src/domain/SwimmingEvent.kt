package com.velocip.io.domain

data class SwimmingEvent(
    val id: String = "",
    var name: String = "",
    val capacity: Int = 0,
    val price: Double = 0.0
)

data class FavouritesEvents(
    val id: String = "",
    val userId: String = "",
    val events: List<SwimmingEvent> = emptyList()
)

data class Event(
    val swimmingEventId: String = "",
    var qty: Int
)

data class User(
    val id: String = "",
    val name: String = "",
    val userName: String = "",
    val password: String = ""
)

