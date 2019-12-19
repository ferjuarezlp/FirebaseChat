package com.ferjuarez.udacity.friendlychat.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferjuarez.udacity.friendlychat.R
import com.ferjuarez.udacity.friendlychat.extensions.inflate
import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage
import kotlinx.android.synthetic.main.item_message.view.*

class MessageAdapter(private val items: ArrayList<FriendlyMessage>, private val listener: (FriendlyMessage) -> Unit): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MessageViewHolder(parent.inflate(R.layout.item_message))

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    fun add(message: FriendlyMessage){
        items.add(0, message)
        notifyDataSetChanged()
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: FriendlyMessage, listener: (FriendlyMessage) -> Unit) = with(itemView) {
            messageTextView.text = item.text
            nameTextView.text = item.name
            //itemImage.loadUrl(item.url)
            setOnClickListener { listener(item) }
        }
    }

}
