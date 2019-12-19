package com.ferjuarez.udacity.friendlychat.presenters

import com.ferjuarez.udacity.friendlychat.repository.MessageRepository
import com.ferjuarez.udacity.friendlychat.ui.BaseView
import java.util.*

class MessagesPresenter(val repo: MessageRepository) {
    private var mView: BaseView? = null
    private val mRepository = repo

    fun subscribe(view: BaseView){
        this.mView = view
    }

    fun subscribeWithReading(view: BaseView){
        this.mView = view
        mRepository.subscribeToListener(view)
    }

    fun sendMessage(message: String, username: String, date: Date){
        mRepository.sendMessage(message, username, date)
    }


}