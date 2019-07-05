package com.example.iutilities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        register_button.setOnClickListener {
            signInUser()
        }

        facebook_login.setOnClickListener {
            Toast.makeText(this, "This is a WIP", Toast.LENGTH_SHORT).show()
        }

        gmail_login.setOnClickListener {
            Toast.makeText(this, "This is a WIP", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInUser()
    {
        val email = email_text.text.toString()
        val password = password_text.text.toString()

        if ( email.isEmpty() || password.isEmpty() )
        {
            Toast.makeText(this, "Please enter email address and password before signing in", Toast.LENGTH_SHORT).show()
        }
        else
        {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Toast.makeText(this, "User signed in successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Marketplace::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "User sign in failed. ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
