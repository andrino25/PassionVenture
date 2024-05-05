package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginView : AppCompatActivity() {

    private val registeredUsers = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)

        val buttonForLoginOrRegister = findViewById<Button>(R.id.logInButton)
        val signUp = findViewById<Button>(R.id.signInButtonLink)
        buttonForLoginOrRegister.setOnClickListener {

            val user = findViewById<EditText>(R.id.userName).text.toString().trim();
            val pass = findViewById<EditText>(R.id.password).text.toString().trim();

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                if ((user == "admin" && pass == "admin") || (user == "admin1" && pass == "admin1")) {
                    // Check for both admin and admin1
                    val intent = if (user == "admin") {
                        Intent(this, HomePageEnthusiastView::class.java)
                    } else {
                        Intent(this, HomePageMentorView::class.java)
                    }
                    startActivity(intent)
                } else {
                    // Incorrect username or password
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Fields are empty
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {
            val intent = Intent(this, RegisterRoleView::class.java)
            startActivity(intent)
        }
    }
}

