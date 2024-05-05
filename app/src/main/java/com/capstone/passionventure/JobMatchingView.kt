package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JobMatchingView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_matching_view)

        val toList = findViewById<Button>(R.id.Button)

        toList.setOnClickListener {
            val intent = Intent (this, JobListView::class.java)
            startActivity(intent)
        }
    }
}