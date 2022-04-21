package com.harin.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.contactUsModule() {
    routing {
        get("/contactus"){
            call.respondText("contacts us!")
        }

        get("/aboutus") {
            call.respondText("about us")
        }
    }
}