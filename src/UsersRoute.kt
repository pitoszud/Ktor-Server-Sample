package com.velocip.io

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.users(){
    get("/users"){
        call.respondText(userData)
    }
}