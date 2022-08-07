package com.odzjeffrey.workoutpartner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etName = findViewById(R.id.etName)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener{
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            signUp(mAuth, name, email, password)
        }

    }

    private fun signUp(mAuth: FirebaseAuth, name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.i("Main Activity", "Connection Successful")
                    addUserToDataBase(mDbRef, name, email, mAuth.currentUser!!.uid)
                    val intent = Intent(applicationContext, ChatsActivity::class.java )
                    finish()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDataBase(mDbRef: DatabaseReference, name: String, email: String, uid: String) {
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}