package com.velocip.io

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


var userData = "{\"users\": [\"Patryk\", \"Andrew\", \"Adrian\", \"Aiden\", ]}"


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        gson {

        }
    }

    val client: HttpClient = HttpClient(Apache) {
        install(JsonFeature){
            serializer = GsonSerializer()
        }
    }


    routing {
        this.apply {
            trace { application.log.trace(it.buildText()) }
            event(client)
            root()
            rootPost()
            users()
            userCount()
            userAccess()
            swimmingEvent()
        }
    }


}


