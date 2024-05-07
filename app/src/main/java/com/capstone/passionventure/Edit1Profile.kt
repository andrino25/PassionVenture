package com.capstone.passionventure

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.imageview.ShapeableImageView
import database.DatabaseHelper
import java.io.ByteArrayOutputStream

class Edit1Profile : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var username: String
    private lateinit var profileImageView: ShapeableImageView
    private lateinit var selectedImageUri: Uri

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit1_profile)

        dbHelper = DatabaseHelper(this)

        // Retrieve the current logged-in username from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        username = sharedPreferences.getString("USERNAME", null).toString()

        // Retrieve user data and populate the EditText fields
        val userData = dbHelper.getUserData(username)
        userData?.let {
            findViewById<EditText>(R.id.nameText).setText(userData.fullName)
            findViewById<EditText>(R.id.emailText).setText(userData.email)
            findViewById<EditText>(R.id.numText).setText(userData.contact)
            findViewById<EditText>(R.id.addEdit).setText(userData.address)

            // Set profile image
            val bitmap = BitmapFactory.decodeByteArray(userData.profileImage, 0, userData.profileImage?.size ?: 0)
            findViewById<ShapeableImageView>(R.id.imageView).setImageBitmap(bitmap)
        }

        profileImageView = findViewById(R.id.imageView)


        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        backToDashboard.setOnClickListener {
            val intent = Intent(this, HomePageMentorView::class.java)
            startActivity(intent)
        }

        // Save button functionality to update user data
        val saveButton: TextView = findViewById(R.id.saveButton)
        val nameEditText: EditText = findViewById(R.id.nameText)
        val emailEditText: EditText = findViewById(R.id.emailText)
        val numEditText: EditText = findViewById(R.id.numText)
        val addEditText: EditText = findViewById(R.id.addEdit)
        val selectImageButton: Button = findViewById(R.id.selectImageButton)

        val nameEditButton: Button = findViewById(R.id.nameEdit)
        val emailEditButton: Button = findViewById(R.id.emailEdit)
        val numEditButton: Button = findViewById(R.id.numEdit)
        val addEditButton: Button = findViewById(R.id.editAdd)

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

        selectImageButton.setOnClickListener {
            profileImageView.isClickable = true
            nameEditText.isEnabled = true

            profileImageView.setOnClickListener {
                openGallery()
            }
        }


        saveButton.setOnClickListener {
            val fullName = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val contact = numEditText.text.toString()
            val address = addEditText.text.toString()

            // Convert ImageView to ByteArray
            val bitmap = (profileImageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val imageData = stream.toByteArray()

            // Update user data in the database
            dbHelper.updateUserData(username, fullName, email, contact, address, imageData)
            Toast.makeText(this, "Profile edited successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                // Update the ImageView with the selected image
                findViewById<ShapeableImageView>(R.id.imageView).setImageURI(selectedImageUri)
            }
        }
    }
}


