package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import database.DatabaseHelper
import database.EnthusiastRegister

class LoginView : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)

        dbHelper = DatabaseHelper(this)

        val buttonForLoginOrRegister = findViewById<Button>(R.id.logInButton)
        val signUp = findViewById<Button>(R.id.signInButtonLink)


        buttonForLoginOrRegister.setOnClickListener {
            val username = findViewById<EditText>(R.id.userName).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (checkUserCredentials(username, password)) {
                    // Save the username in SharedPreferences upon successful login
                    saveUsername(username)

                    val category = dbHelper.getUserCategory(username)
                    if (category == "Enthusiast"){
                        val intent = Intent(this, HomePageEnthusiastView::class.java)
                        startActivity(intent)
                    } else if (category == "Mentor"){
                        val intent = Intent(this, HomePageMentorView::class.java)
                        startActivity(intent)
                    }
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

    private fun checkUserCredentials(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "${EnthusiastRegister.UserEntry.COLUMN_USERNAME} = ? AND ${EnthusiastRegister.UserEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(
            EnthusiastRegister.UserEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    private fun saveUsername(username: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("USERNAME", username)
        editor.apply()
    }
}


