package com.example.room

import com.example.data.MessageDataSource
import com.example.data.model.Message
import io.ktor.http.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messageDataSource: MessageDataSource
) {
    private val member = ConcurrentHashMap<String, Member>()

    fun onJoin(
        username:String,
        sessionId: String,
        socket: WebSocketSession
    ){
        if (member.contains(username)){
            throw MemberAlreadyExistsException()
        }
        member[username] = Member(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    fun sendMessage(senderUsername: String, message: String){
        member.values.forEach{member->
            val messageEntity = Message(
                text = message,
                username = senderUsername,
                timestamp = System.currentTimeMillis()
            )
            messageDataSource.
        }
    }
}