package com.harin

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.harin.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.database.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/notes",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "Harin@123"
        )

        install(ContentNegotiation){ // we can declare this on our route function also
            json()
        }
        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
