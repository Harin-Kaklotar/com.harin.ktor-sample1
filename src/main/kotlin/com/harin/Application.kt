package com.harin

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.harin.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation){ // we can declare this on our route function also
            json()
        }
        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
