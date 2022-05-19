package com.example.data

import com.example.data.model.Message

class MessageDataSourceImpl: MessageDataSource {
    override suspend fun getAllMessages(): List<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMessage(message: Message) {
        TODO("Not yet implemented")
    }
}