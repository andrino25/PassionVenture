package com.capstone.passionventure

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import database.DatabaseHelper

class HomePageMentorView : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_mentor_view)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        username = sharedPreferences?.getString("USERNAME", null)
        val dbHelper = DatabaseHelper(this)

        val profileImageView = findViewById<ImageView>(R.id.imageView5)

        val greetingTextView = findViewById<TextView>(R.id.textView2)
        greetingTextView.text = "Welcome, $username"

        val imageData = username?.let { dbHelper.getUserImage(it) }
        if (imageData != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
            profileImageView.setImageBitmap(bitmap)
        } else {
            // Use a default image or handle the absence of image data as needed
        }

    }

    override fun onResume() {
        super.onResume()

        // Update user data upon returning to the activity
        val dbHelper = DatabaseHelper(this)
        val greetingTextView = findViewById<TextView>(R.id.textView2)
        greetingTextView.text = "Welcome, $username"

        val profileImageView = findViewById<ImageView>(R.id.imageView5)
        val imageData = username?.let { dbHelper.getUserImage(it) }
        if (imageData != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
            profileImageView.setImageBitmap(bitmap)
        } else {
            // Set a default image if no image is available
            profileImageView.setImageResource(R.drawable.pfimage3_removebg_preview)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this, Edit1Profile::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }
            R.id.messages -> {
                val intent = Intent(this, Message1View::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }

            R.id.logout -> {

                val intent = Intent(this, LoginView::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}