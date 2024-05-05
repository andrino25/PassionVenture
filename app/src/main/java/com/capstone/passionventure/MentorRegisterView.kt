package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MentorRegisterView : AppCompatActivity() {

    private val registeredUsers = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_register_view)

        val login = findViewById<Button>(R.id.signUpButtonLink)
        val backBtn = findViewById<Button>(R.id.BtnBack)
        val registerBtn = findViewById<Button>(R.id.registerButton)

        login.setOnClickListener {

            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)

        }

        backBtn.setOnClickListener {

            val intent = Intent(this, RegisterRoleView::class.java)
            startActivity(intent)

        }

        registerBtn.setOnClickListener {

            val userName = findViewById<EditText>(R.id.userName).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()
            val email = findViewById<EditText>(R.id.email).text.toString().trim()
            val confirmPassword = findViewById<EditText>(R.id.email).text.toString().trim()

            if (userName.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {


                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginView::class.java)
                    startActivity(intent)
                } else {
                    // Passwords do not match
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Fields are empty
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }

        }

    }

    // dipa ni mao hehe

    private fun register(username: String, password: String, email: String) {

        if (registeredUsers.any { it.username == username }) {
            // Username already exists
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show()
            return
        }

        // If username is unique, create a new User instance and add it to the list
        val newUser = User(username, password, email)
        registeredUsers.add(newUser)
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
    }

}