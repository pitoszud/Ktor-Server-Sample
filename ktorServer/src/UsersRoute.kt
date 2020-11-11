package com.velocip.io

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.users(){
    get("/users"){
        call.respondText(userData)
    }

    post("users"){
        userData += call.receiveText()
        call.respondText("User added", contentType = ContentType.Text.Plain)
    }
}

fun Routing.user(userId: String){
    get("/users/$userId"){
        call.respondText(userData)
    }
}