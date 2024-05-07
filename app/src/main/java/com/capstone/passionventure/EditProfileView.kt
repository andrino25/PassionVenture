package com.capstone.passionventure

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.imageview.ShapeableImageView
import database.DatabaseHelper
import java.io.ByteArrayOutputStream

class EditProfileView : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_view)

        dbHelper = DatabaseHelper(this)

        // Retrieve logged-in user's username from Intent
        username = intent.getStringExtra("username").toString()

        // Fetch user's data from the database based on username
        val userData = dbHelper.getUserData(username)

        // Assign user's data to corresponding EditText fields
        findViewById<EditText>(R.id.nameText).setText(userData?.fullName)
        findViewById<EditText>(R.id.emailText).setText(userData?.email)
        findViewById<EditText>(R.id.numText).setText(userData?.contact)
        findViewById<EditText>(R.id.addEdit).setText(userData?.address)
        val bitmap = BitmapFactory.decodeByteArray(userData?.profileImage, 0, userData?.profileImage?.size ?: 0)
        findViewById<ShapeableImageView>(R.id.imageView).setImageBitmap(bitmap)

        val imageView = findViewById<ShapeableImageView>(R.id.imageView)

        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        backToDashboard.setOnClickListener {
            val intent = Intent(this, HomePageEnthusiastView::class.java)
            startActivity(intent)
        }

        //Editing Profile condition ari ibutang

        val nameEditButton: Button = findViewById(R.id.nameEdit)
        val emailEditButton: Button = findViewById(R.id.emailEdit)
        val numEditButton: Button = findViewById(R.id.numEdit)
        val addEditButton: Button = findViewById(R.id.editAdd)
        val selectImageButton: Button = findViewById(R.id.selectImageButton)
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

        selectImageButton.setOnClickListener {
            imageView.isClickable = true
            nameEditText.isEnabled = true

            imageView.setOnClickListener {
                openGallery()
            }
        }

        saveButton.setOnClickListener {
            Toast.makeText(this, "Profile edited successfully", Toast.LENGTH_SHORT).show()

            // Update user's data in the database
            val updatedName = nameEditText.text.toString()
            val updatedEmail = emailEditText.text.toString()
            val updatedNum = numEditText.text.toString()
            val updatedAdd = addEditText.text.toString()

            val drawable = imageView.drawable
            val bitmap = (drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val imageData = stream.toByteArray()

            dbHelper.updateUserData(username, updatedName, updatedEmail, updatedNum, updatedAdd, imageData)

            // Disable all EditText fields
            nameEditText.isEnabled = false
            emailEditText.isEnabled = false
            numEditText.isEnabled = false
            addEditText.isEnabled = false
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


    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

}

