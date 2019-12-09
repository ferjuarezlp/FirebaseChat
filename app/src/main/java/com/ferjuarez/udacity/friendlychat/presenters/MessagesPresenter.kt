package com.ferjuarez.udacity.friendlychat.presenters

import android.view.View
import com.ferjuarez.udacity.friendlychat.repository.MessageRepository
import com.ferjuarez.udacity.friendlychat.ui.BaseView

class MessagesPresenter(val repo: MessageRepository) {
    private var mView: BaseView? = null
    private val mRepository = repo

    fun subscribe(view: BaseView){
        this.mView = view
    }

    fun sendMessage(messsage: String, username: String){
        mRepository.sendMessage(messsage, username)
    }

    fun sayHello() = "${repo.giveHello()} from $this"
}