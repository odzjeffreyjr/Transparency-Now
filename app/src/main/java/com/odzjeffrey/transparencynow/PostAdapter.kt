package com.odzjeffrey.transparencynow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val context: Context, private val postList: ArrayList<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val postUsername: TextView = itemView.findViewById<TextView>(R.id.postUsername)
        val postImageView: ImageView = itemView.findViewById<ImageView>(R.id.postImageView)
        val postProfilePicture: ImageView = itemView.findViewById<ImageView>(R.id.postProfilePicture)
        val postTitle: TextView = itemView.findViewById<TextView>(R.id.postTitle)
        val postItemMessageBox: TextView = itemView.findViewById<TextView>(R.id.postItemMessageBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.postUsername.text = currentPost.postUsername
        holder.postImageView.setBackgroundResource(currentPost.postImageView)
        holder.postProfilePicture.setBackgroundResource(currentPost.postProfilePicture)
        holder.postTitle.text = currentPost.postTitle
        holder.postItemMessageBox.text = currentPost.blab
//        holder.itemView.setOnClickListener{
//            val intent = Intent(context, ChatActivity::class.java)
//            intent.putExtra("name", currentUser.name)
//            intent.putExtra("uid", currentUser.uid)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}