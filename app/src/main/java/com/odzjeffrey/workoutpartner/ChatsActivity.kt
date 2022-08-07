package com.odzjeffrey.workoutpartner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatsActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var backButtonChatsActivity: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        val toolbar: Toolbar? = findViewById(R.id.chatsActivityToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title= this.getString(R.string.app_name)

        mAuth = Firebase.auth
        mDbRef =Firebase.database.getReference()

        userList = ArrayList()

        backButtonChatsActivity = findViewById(R.id.backButtonChatsActivity)
        backButtonChatsActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        adapter = UserAdapter(this, userList)
        rvUser = findViewById(R.id.rvUser)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = adapter

        mDbRef.child("user").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)

                    if(mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                mAuth.signOut()
                val intent = Intent(this@ChatsActivity, Login::class.java)
                finish()
                startActivity(intent)
                return true}

        }
        return super.onOptionsItemSelected(item)
    }
}