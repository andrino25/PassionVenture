package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CareerGuidanceView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career_guidance_view)

        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        // Handle back button click
        backToDashboard.setOnClickListener {
            val intent = Intent(this, HomePageEnthusiastView::class.java)
            startActivity(intent)
        }

    }
}