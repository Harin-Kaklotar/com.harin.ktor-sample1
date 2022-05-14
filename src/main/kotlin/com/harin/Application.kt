package com.harin

import com.harin.entities.NoteEntity
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.harin.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

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
//        database.insert(NoteEntity){
//            // no need to add id because it's auto increment
//            set(NoteEntity.note, "Learning ktor with ktorm")
//        }
//
//        database.insert(NoteEntity){
//            set(NoteEntity.note, "Attend wedding")
//        }
//
//        database.insert(NoteEntity){
//            set(NoteEntity.note, "Go for walk")
//        }
//
//        database.insert(NoteEntity){
//            set(NoteEntity.note, "talk with mom and dad")
//        }

        // database fetch operation
        // fetch all data form 'note' table
//        val notes = database.from(NoteEntity).select()
//        for (row in notes){
//            println("${row[NoteEntity.id]}: ${row[NoteEntity.note]}")
//        }

        // update item, pass table entity as parameter where we want's to update
        //database.update(NoteEntity){
        //    // set value which we want's to update
        //    set(it.note, "Learning Ktor + Ktorm + ySql")
        //    // put condition where we want's to update that value
        //    where { it.id eq 1 }
        //}
//
        //database.update(NoteEntity){
        //    set(it.note, "make habit of daily morning walk")
        //    where { it.id eq 4 }
        //}

        // delete item from table
        // pass the entity object as parameter
        database.delete(NoteEntity){
            // this is where close so here we have to pass he conditions
            it.id eq 5
        }

        install(ContentNegotiation){ // we can declare this on our route function also
            json()
        }
        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
