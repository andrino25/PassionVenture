package com.capstone.passionventure

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import database.DatabaseHelper

class HomePageEnthusiastView : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_enthusiast_view)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        username = sharedPreferences?.getString("USERNAME", null)

        val goToToken = findViewById<ImageButton>(R.id.BtnRecharge)
        val careerBtn = findViewById<ImageButton>(R.id.careerGuidanceButton)
        val mentorBtn = findViewById<ImageButton>(R.id.mentorButton)
        val resourceBtn = findViewById<ImageButton>(R.id.lmsButton)
        val jobBtn = findViewById<ImageButton>(R.id.jobMatchingButton)
        //depends on what user logs in
        val dbHelper = DatabaseHelper(this)
        val profileImageView = findViewById<ImageView>(R.id.profileImageView)

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        greetingTextView.text = "Hola, $username"

        val imageData = username?.let { dbHelper.getUserImage(it) }
        if (imageData != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
            profileImageView.setImageBitmap(bitmap)
        } else {
            // Use a default image or handle the absence of image data as needed
        }

        resourceBtn.setOnClickListener {

            val intent = Intent(this, ResourcesView::class.java)
            startActivity(intent)

        }

        jobBtn.setOnClickListener {

            val intent = Intent(this, JobMatchingView::class.java)
            startActivity(intent)

        }

        mentorBtn.setOnClickListener {

            val intent = Intent(this, MentorView::class.java)
            startActivity(intent)

        }

        careerBtn.setOnClickListener {

            val intent = Intent(this, CareerGuidanceView::class.java)
            startActivity(intent)

        }
        goToToken.setOnClickListener {

            val intent = Intent(this, PassionTokenView::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()

        // Update user data upon returning to the activity
        val dbHelper = DatabaseHelper(this)
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        greetingTextView.text = "Hola, $username"

        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
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
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this, EditProfileView::class.java)
                intent.putExtra("username", username)
                startActivity(intent)

                return true // Move the return statement inside each branch of when
            }
            R.id.messages -> {
                val intent = Intent(this, MessageView::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }
            R.id.resources -> {

                return true
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
