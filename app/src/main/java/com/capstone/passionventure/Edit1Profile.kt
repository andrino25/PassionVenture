package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Edit1Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit1_profile)
        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        backToDashboard.setOnClickListener {

            val intent = Intent(this, HomePageMentorView::class.java)
            startActivity(intent)

        }

        //Editing Profile condition ari ibutang

        val nameEditButton: Button = findViewById(R.id.nameEdit)
        val emailEditButton: Button = findViewById(R.id.emailEdit)
        val numEditButton: Button = findViewById(R.id.numEdit)
        val addEditButton: Button = findViewById(R.id.editAdd)
        val saveButton: TextView = findViewById(R.id.saveButton)

        val nameEditText: EditText = findViewById(R.id.nameText)
        val emailEditText: EditText = findViewById(R.id.emailText)
        val numEditText: EditText = findViewById(R.id.numText)
        val addEditText: EditText = findViewById(R.id.addEdit)

        //enabled all the edit text if the edit icon is clicked
        nameEditButton.setOnClickListener {
            nameEditText.isEnabled = true
        }

        emailEditButton.setOnClickListener {
            emailEditText.isEnabled = true
        }

        numEditButton.setOnClickListener {
            numEditText.isEnabled = true
        }

        addEditButton.setOnClickListener {
            addEditText.isEnabled = true
        }

        saveButton.setOnClickListener {

            Toast.makeText(this, "Profile edited successfully", Toast.LENGTH_SHORT).show()
            // Disable all balik
            nameEditText.isEnabled = false
            emailEditText.isEnabled = false
            numEditText.isEnabled = false
            addEditText.isEnabled = false
        }

    }
}