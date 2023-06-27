package com.odzjeffrey.transparencynow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForumAdapter(val context: Context, private val forumList: ArrayList<Forum>): RecyclerView.Adapter<ForumAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTopic: TextView = itemView.findViewById<TextView>(R.id.tvTopic)
        val tvNewMessages: TextView = itemView.findViewById<TextView>(R.id.tvNewMessages)
        val tvPreview: TextView = itemView.findViewById<TextView>(R.id.tvPreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentForum = forumList[position]
        holder.tvTopic.text = currentForum.topic
        holder.tvNewMessages.text = currentForum.newMessages
        holder.tvPreview.text = currentForum.preview
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentForum.topic)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return forumList.size
    }
}