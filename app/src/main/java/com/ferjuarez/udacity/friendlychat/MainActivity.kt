package com.ferjuarez.udacity.friendlychat

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ferjuarez.udacity.friendlychat.presenters.MessagesPresenter
import com.ferjuarez.udacity.friendlychat.ui.BaseView
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseView() {

    private val TAG = "MainActivity"
    private val ANONYMOUS = "anonymous"
    private val DEFAULT_MSG_LENGTH_LIMIT = 1000
    private var mUsername: String? = null
    val firstPresenter: MessagesPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        firstPresenter.subscribe(this as BaseView)
        firstPresenter.sayHello()
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
            firstPresenter.sendMessage(messageEditText.text.toString(), ANONYMOUS)
            messageEditText.setText("")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
