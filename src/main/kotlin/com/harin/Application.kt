package com.harin

import com.harin.entities.NoteEntity
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.harin.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.database.Database
import org.ktorm.dsl.insert

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

        // initialize database connection
        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/notes",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "Harin@123"
        )

        // insert item into the database
        // pass table object as parameter where we perform insert operation
        database.insert(NoteEntity){
            // no need to add id because it's auto increment
            set(NoteEntity.note, "Learning ktor with ktorm")
        }

        database.insert(NoteEntity){
            set(NoteEntity.note, "Attend wedding")
        }

        database.insert(NoteEntity){
            set(NoteEntity.note, "Go for walk")
        }

        database.insert(NoteEntity){
            set(NoteEntity.note, "talk with mom and dad")
        }

        install(ContentNegotiation){ // we can declare this on our route function also
            json()
        }
        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
