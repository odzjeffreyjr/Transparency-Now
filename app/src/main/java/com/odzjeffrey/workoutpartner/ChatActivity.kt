package com.odzjeffrey.workoutpartner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    private lateinit var rvChat: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var ivMessage: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var backButtonChatActivity: CardView

    private var receiverRoom: String? = null
    private var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        mDbRef = Firebase.database.getReference()

        backButtonChatActivity = findViewById(R.id.backButtonChatActivity)
        backButtonChatActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid
        supportActionBar?.title = name

        rvChat = findViewById(R.id.rvChat)
        messageBox=findViewById(R.id.messageBox)
        ivMessage = findViewById(R.id.ivMessage)

        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)

        rvChat.adapter = messageAdapter
        rvChat.layoutManager = LinearLayoutManager(this)

//adding data to recyclerView
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                        messageAdapter.notifyDataSetChanged()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        ivMessage.setOnClickListener {
            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("messages")
                .push()
                .setValue(messageObject)
                .addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages")
                        .push()
                }
            messageBox.setText("")
        }
    }
}