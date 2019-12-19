package com.ferjuarez.udacity.friendlychat.ui

import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage

interface BaseView{
    fun addNewMessage(message: FriendlyMessage)
}