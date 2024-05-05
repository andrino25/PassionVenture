package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class VerifyCredentials1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vreify_credentials1)

        val nextBtn = findViewById<Button>(R.id.buttonNext)

        nextBtn.setOnClickListener {

            val intent = Intent(this, VerifyCredentials2::class.java)
            startActivity(intent)

        }
    }
}