package com.ktor.sample.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import java.util.*

data class SwimmingEvent(
    val id: String = "",

    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    var name: String = "",
    val capacity: Int = 0,
    val price: Double = 0.0,

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    val created: LocalDateTime = LocalDateTime.now()
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

