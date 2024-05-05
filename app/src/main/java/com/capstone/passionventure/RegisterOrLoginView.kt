package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterOrLoginView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_or_login_view)

        val login = findViewById<Button>(R.id.logInButton)
        val register = findViewById<Button>(R.id.registerButton)

        login.setOnClickListener {

            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)

        }

        register.setOnClickListener {

            val intent = Intent(this, RegisterRoleView::class.java)
            startActivity(intent)

        }

    }
}