package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PassionTokenView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passion_token_view)

        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        backToDashboard.setOnClickListener {

            val intent = Intent(this, HomePageEnthusiastView::class.java)
            startActivity(intent)

        }

    }
}