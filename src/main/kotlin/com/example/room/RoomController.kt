package com.example.room

import com.example.data.MessageDataSource
import com.example.data.model.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.forEach
import kotlin.collections.set

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

    suspend fun sendMessage(senderUsername: String, message: String){
        member.values.forEach{member->
            val messageEntity = Message(
                text = message,
                username = senderUsername,
                timestamp = System.currentTimeMillis()
            )
            messageDataSource.insertMessage(messageEntity)

            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))


        }
    }
}