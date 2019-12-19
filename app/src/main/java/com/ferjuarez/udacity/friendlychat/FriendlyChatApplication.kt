package com.ferjuarez.udacity.friendlychat

import android.app.Application
import com.ferjuarez.udacity.friendlychat.presenters.MessagesPresenter
import com.ferjuarez.udacity.friendlychat.repository.MessageRepository
import com.ferjuarez.udacity.friendlychat.repository.MessageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FriendlyChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single<MessageRepository> { MessageRepositoryImpl() }
            factory { MessagesPresenter(get()) }
        }

        startKoin{
            androidLogger()
            androidContext(this@FriendlyChatApplication)
            modules(appModule)
        }
    }
}