package com.velocip.io

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.users() {
    get("/users") {
        call.respondText(userData)
    }

    post("users") {
        userData += call.receiveText()
        call.respondText("User added", contentType = ContentType.Text.Plain)
    }
}

fun Routing.user(userId: String) {
    get("/users/$userId") {
        call.respondText(userData)
    }
}


fun Routing.userCount() {

    // http://127.0.0.1:8080/users/active
    route("/users") {
        get("/active") {
            call.respondText {
                userData
            }
        }


        // http://127.0.0.1:8080/users/types?userType=Patryk
        route("/types", HttpMethod.Get) {
            param("userType") {
                handle {
                    val userType = call.parameters["userType"]
                    call.respondText {
                        "Data for 1 user $userType"
                    }
                }
            }
        }
    }
}


fun Routing.userAccess() {


    route("/users") {
        // http://127.0.0.1:8080/users/count?userType=Patryk&access_token=abcdefghijklmnoprstuwz
        // http://127.0.0.1:8080/users/count?access_token=abcdefghijklmnoprstuwz&userType=Patryk
        // http://127.0.0.1:8080/users/count?access_token=abcdefghijklmnoprstuwz
        route("/count", HttpMethod.Get) {
            header("access_token", "abcdefghijklmnoprstuwz") {
                param("userType") {
                    handle {
                        val userType = call.parameters["userType"]
                        call.respondText {
                            "${userData.length} $userType users"
                        }
                    }
                }
            }
        }
    }
}