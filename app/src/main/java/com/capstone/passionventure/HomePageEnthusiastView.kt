package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class HomePageEnthusiastView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_enthusiast_view)

        val goToToken = findViewById<ImageButton>(R.id.BtnRecharge)
        val careerBtn = findViewById<ImageButton>(R.id.careerGuidanceButton)
        val mentorBtn = findViewById<ImageButton>(R.id.mentorButton)
        val resourceBtn = findViewById<ImageButton>(R.id.lmsButton)
        val jobBtn = findViewById<ImageButton>(R.id.jobMatchingButton)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this, EditProfileView::class.java)
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
