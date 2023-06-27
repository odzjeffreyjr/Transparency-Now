package com.odzjeffrey.transparencynow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        } else{
            return ITEM_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ReceivedViewHolder(LayoutInflater.from(context).inflate(R.layout.received, parent, false))
        } else {
            return ReceivedViewHolder(LayoutInflater.from(context).inflate(R.layout.sent, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass == SentViewHolder::class.java){
            val currentMessage = messageList[position]
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        }else{
            val currentMessage = messageList[position]
            val viewHolder = holder as ReceivedViewHolder
            holder.receivedMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    inner class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage  = itemView.findViewById<TextView>(R.id.tvSentMessage)
    }

    inner class ReceivedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receivedMessage  = itemView.findViewById<TextView>(R.id.tvReceivedMessage)
    }

}