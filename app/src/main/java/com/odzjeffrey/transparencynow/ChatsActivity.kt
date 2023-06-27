package com.odzjeffrey.transparencynow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatsActivity : AppCompatActivity() {
/*
This code employs 2 adapter. The forum adapter and the user adapter. In future, comment out the forum adapter and restore the user adapter.
 */
    private lateinit var rvUser: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var forumList: ArrayList<Forum>
    private lateinit var adapter: UserAdapter
    private lateinit var adapter2: ForumAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var backButtonChatsActivity: CardView
    private lateinit var floatingActionButtonChatsActivity: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
//        val toolbar: Toolbar? = findViewById(R.id.chatsActivityToolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.title= this.getString(R.string.app_name)

        mAuth = Firebase.auth
        mDbRef =Firebase.database.getReference()

        userList = ArrayList()
        forumList = initForumList()

        backButtonChatsActivity = findViewById(R.id.backButtonChatsActivity)
        floatingActionButtonChatsActivity  = findViewById<FloatingActionButton>(R.id.floatingActionButtonChatsActivity)
        backButtonChatsActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        floatingActionButtonChatsActivity.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        adapter2 = ForumAdapter(this, forumList)
        rvUser = findViewById(R.id.rvUser)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = adapter2
//        adapter = UserAdapter(this, userList)
//        rvUser = findViewById(R.id.rvUser)
//        rvUser.layoutManager = LinearLayoutManager(this)
//        rvUser.adapter = adapter

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
    private fun initForumList(): ArrayList<Forum>{
        val list = ArrayList<Forum>()
        val a = Forum("Fighting Corruption: Tips and Strategies", "99+", "But I think that if you think about it deeply, it is actually possible")
        val b = Forum("Investigative journalism vs Corruption", "74", "Yes. I remember the case of Jane Ritah, the senegalese journalist")
        val c = Forum("Open data tools for accountability", "29", "I think even Transparency ")
        val d = Forum("Preventing conflict of interest in government", "1", "I agree. What do the rest of you think?")
        val e = Forum("The cost of corruption: Impact on Society", "78", "Yes brother haha.")
        val f = Forum("Whistleblowing and Protecting whistleblowers", "36", "Last year it happened to Wava Muno. It could happen again")
        val g = Forum("Investigating government accountability", "99+", "I urge you to check this month's report.")
        val h = Forum("The role of civil society in accountability", "76", "But then they keep embezzling the money fearlessly.")
        val i = Forum("Promoting ethical leadership in public service", "1", "I think ethics is the biggest problem facing Africa.")
        val j = Forum("The impact of corruption on Development", "57", "Look at Apac District. There are no good roads at all")
        val k = Forum("Strengthening Anti-Corruption Regulations", "44", "Is there a policy already?")
        val l = Forum("The importance of public access to information", "30", "Does Transparency have access to budget documents?")
        val m = Forum("Combating bribery and extortion in government", "99+", "Corrupt. Extremely corrupt!")
        val n = Forum("Democracy: Ensuring free and fair processes", "5", "Sure sure. The last elections were similar.")

        list.add(a)
        list.add(b)
        list.add(c)
        list.add(d)
        list.add(e)
        list.add(f)
        list.add(g)
        list.add(h)
        list.add(i)
        list.add(j)
        list.add(k)
        list.add(l)
        list.add(m)
        list.add(n)
        return list
    }
}