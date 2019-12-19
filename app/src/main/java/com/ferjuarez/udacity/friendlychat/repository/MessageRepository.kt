package com.ferjuarez.udacity.friendlychat.repository

import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage
import com.ferjuarez.udacity.friendlychat.ui.BaseView
import com.google.firebase.database.*
import java.util.*

interface  MessageRepository {
    fun sendMessage(message: String, username: String, date: Date)
    fun subscribeToListener(view: BaseView)
}

class MessageRepositoryImpl : MessageRepository {

    private val mFirebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mFirebaseDatabaseRef: DatabaseReference

    init {
        this.mFirebaseDatabaseRef = mFirebaseDatabase.reference.child("messages")
    }

    override fun sendMessage(message: String, username: String, date: Date){
        val message = FriendlyMessage(
            message,
            username,
            "",
            date
        )
        mFirebaseDatabaseRef.push().setValue(message)
    }

    override fun subscribeToListener(view: BaseView) {
        val childEventListener = object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val message = dataSnapshot.getValue(FriendlyMessage::class.java)
                view.addNewMessage(message!!)
            }
        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        mFirebaseDatabaseRef.addChildEventListener(childEventListener)
    }

}