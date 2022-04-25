package com.harin.plugins

import com.harin.model.request.LoginRequest
import com.harin.model.request.UserResponse
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.io.File

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

        get("/user"){

            // we can send any error code and message as we want
            //call.respondText("Time out error", status = HttpStatusCode.GatewayTimeout)
            //call.respondText("Not found error", status = HttpStatusCode.NotFound)
            //call.respondText("My error message", status = HttpStatusCode.MethodNotAllowed)

            val userResponse = UserResponse("John", "john@gmail.com")
            call.respond(userResponse)
        }

        /**
         * when we don't want to send some information in response
         * we can send it in headers
         * we can send custom headers with values
         */
        get("/headers"){
            call.response.headers.append("server-name", "KtorServer")
            call.response.headers.append("chocolate", "Dairy-Milk & KitKat")
            call.respondText("Just check header!")
        }

        /**
         * file download from browser
         */
        get("/fileDownload"){

            val downloadingFile = File("file/mickey_mouse.jpeg")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName, "mickey_mouse.jpeg"
                ).toString()
            )

            call.respondFile(downloadingFile)

        }

        /**
         * open file in browser
         */
        get("/fileOpen"){

            val downloadingFile = File("file/minnie_mouse.jpeg")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Inline.withParameter(
                    ContentDisposition.Parameters.FileName, "minnie_mouse.jpeg"
                ).toString()
            )

            call.respondFile(downloadingFile)

        }

    }
}
