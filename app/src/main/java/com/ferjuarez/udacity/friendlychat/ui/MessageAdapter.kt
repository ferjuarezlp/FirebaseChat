package com.ferjuarez.udacity.friendlychat.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ferjuarez.udacity.friendlychat.model.FriendlyMessage

class MessageAdapter(context: Context, resource: Int, list: ArrayList<FriendlyMessage>): ArrayAdapter<FriendlyMessage>(context, resource, list) {
    var resource: Int
    var list: ArrayList<FriendlyMessage>
    var vi: LayoutInflater

    init {
        this.resource = resource
        this.list = list
        this.vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var holder: ViewHolder
        var view: View? = null

        if(convertView == null){
            view = vi.inflate(resource, null) //error in this line
            holder = ViewHolder()

            //.photoImageView = view.findViewById(R.id.myImage) as ImageView?

            //view.tag(holder) //error in this line

        } else {
            holder = convertView.tag as ViewHolder
        }

        return view!!
    }

    internal class ViewHolder {
        var photoImageView: ImageView? = null
        var message: TextView? = null
        var author: TextView? = null
    }
}