package com.ferjuarez.udacity.friendlychat.repository

import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface  MessageRepository {
    fun giveHello(): String
    fun sendMessage(message: String, username: String)
}

class MessageRepositoryImpl : MessageRepository {
    private val mFirebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mFirebaseDatabaseRef: DatabaseReference

    init {
        this.mFirebaseDatabaseRef = mFirebaseDatabase.reference.child("messages")
    }

    override fun giveHello() = "Hello Koin"

    override fun sendMessage(message: String, username: String){
        val message = FriendlyMessage(
            message,
            username,
            ""
        )
        mFirebaseDatabaseRef.push().setValue(message)
    }

}