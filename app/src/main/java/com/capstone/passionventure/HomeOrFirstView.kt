package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeOrFirstView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_or_first_view)

        val buttonForLoginOrRegister = findViewById<Button>(R.id.getStartedButton)
        buttonForLoginOrRegister.setOnClickListener {

            val intent = Intent(this, RegisterOrLoginView::class.java)
            startActivity(intent)

        }
    }


}