package com.velocip.io

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.root(){
    get("/"){
        call.respondText("Velocip", ContentType.Text.Plain)
    }
}


fun Routing.rootPost(){
    post("/") {
        val post = call.receive<String>()
        call.respondText("Received $post from the post body", ContentType.Text.Plain)
    }
}


