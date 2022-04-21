package com.harin

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.harin.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
