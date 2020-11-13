package com.velocip.io

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


var userData = "{\"users\": [\"Patryk\", \"Andrew\", \"Adrian\", \"Aiden\", ]}"


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation){
        gson {

        }
    }


    routing { this.apply {

            root()
            rootPost()
            users()
            userCount()
        }
    }

    
}


