package com.example.route

import com.example.room.RoomController
import com.example.session.ChatSession
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

fun Route.chatSocket(roomController: RoomController){
    webSocket("/chat-socket") {
        val session = call.sessions.get<ChatSession>()
        if (session == null){
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY,"No session."))
        }
    }
}