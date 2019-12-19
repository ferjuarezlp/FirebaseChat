package com.ferjuarez.udacity.friendlychat

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage
import com.ferjuarez.udacity.friendlychat.presenters.MessagesPresenter
import com.ferjuarez.udacity.friendlychat.ui.BaseView
import com.ferjuarez.udacity.friendlychat.ui.MessageAdapter
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), BaseView {

    private val TAG = "MainActivity"
    private val ANONYMOUS = "anonymous"
    private val DEFAULT_MSG_LENGTH_LIMIT = 1000
    private var mUsername: String? = null
    private var messageAdapter: MessageAdapter? = null
    val firstPresenter: MessagesPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        firstPresenter.subscribeWithReading(this as BaseView)
        mUsername = ANONYMOUS


        progressBar.visibility = View.INVISIBLE
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                sendButton.isEnabled = charSequence.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        messageEditText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                DEFAULT_MSG_LENGTH_LIMIT
            )
        )

        sendButton.setOnClickListener {
            firstPresenter.sendMessage(messageEditText.text.toString(), ANONYMOUS, Calendar.getInstance().time)
            messageEditText.setText("")
        }

        val friendlyMessages = ArrayList<FriendlyMessage>()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = false
        layoutManager.isSmoothScrollbarEnabled = true
        recyclerMessage.layoutManager = layoutManager


        messageAdapter = MessageAdapter(friendlyMessages) {
            //toast("${it.title} Clicked")
        }
        recyclerMessage.adapter = messageAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun addNewMessage(message: FriendlyMessage) {
        Log.e("","")
        messageAdapter!!.add(message)
    }
}
