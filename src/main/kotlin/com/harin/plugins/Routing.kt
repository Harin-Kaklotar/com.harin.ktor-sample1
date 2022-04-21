package com.harin.plugins

import com.harin.model.request.LoginRequest
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/") {

            println("URI : ${call.request.uri}")

            println("Headers : ${call.request.headers}")
            println("Headers :  ${call.request.headers.names()}")
            println("User-Agent : ${call.request.headers["User-Agent"]}")
            println("Accept : ${call.request.headers["Accept"]}")

            println("Query Parameters : ${call.request.queryParameters}")
            println("Query Parameters : ${call.request.queryParameters.names()}")
            println("name  : ${call.request.queryParameters["name"]}")
            println("email  : ${call.request.queryParameters["email"]}")

            call.respondText("Hello World!")
        }

        /**
         * when we are working with url parameters
         */
        get("/iphones/{page}"){
            // we can get any url parameters using below syntax
            val pageNumber = call.parameters["page"]
            call.respondText("You are on page number : ${pageNumber}")
        }
        get("/user/{userId}"){
            val userId = call.parameters["userId"]
            call.respondText("You have requested for the user id = ${userId}")
        }
        get("/user/{userId}/name"){
            val userId = call.parameters["userId"]
            call.respondText("You have requested for the user id = ${userId}, and the Name of user is John")
        }


        /**
         * when request is with body parameters like
         * {
         *     "email" : "john@gmail.com",
         *     "password" : "123"
         * }
         */
        post("/login"){

            // if user not send any field then this steps throw kotlinx.serialization.MissingFieldException
            val loginRequest = call.receive<LoginRequest>()
            println("email : ${loginRequest.email}")
            println("password : ${loginRequest.password}")
            call.respondText("User login success.")

            // we can handle that exception like below
            //try {
            //    val loginRequest = call.receive<LoginRequest>()
            //    println("email : ${loginRequest.email}")
            //    println("password : ${loginRequest.password}")
            //}catch (exception: Exception){
            //    exception.printStackTrace()
            //}

        }
    }
}
