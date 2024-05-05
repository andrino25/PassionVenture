package com.capstone.passionventure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class JobDetailsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details_view)

        val jobDescription = intent.getStringExtra("jobDescription")
        val jobCompany = intent.getStringExtra("jobCompany")
        val jobCategory = intent.getStringExtra("jobCategory")
        val jobDetail1 = intent.getStringExtra("jobDetails")

        // Find UI elements in your layout
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val companyTextView = findViewById<TextView>(R.id.companyTextView)
        val categoryTextView = findViewById<TextView>(R.id.categoryTextView)
        val detail1TextView = findViewById<TextView>(R.id.detail1TextView)

        // Populate UI elements with job details
        descriptionTextView.text = jobDescription
        companyTextView.text = jobCompany
        categoryTextView.text = jobCategory
        detail1TextView.text = jobDetail1

    }
}