package com.capstone.passionventure

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import database.DatabaseHelper
import java.io.ByteArrayOutputStream

class MentorRegisterView : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_register_view)

        dbHelper = DatabaseHelper(this)
        profileImageView = findViewById(R.id.profileImageView)

        val login = findViewById<Button>(R.id.signUpButtonLink)
        val backBtn = findViewById<Button>(R.id.BtnBack)
        val registerBtn = findViewById<Button>(R.id.registerButton)
        profileImageView = findViewById(R.id.profileImageView)

        login.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, RegisterRoleView::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            val userName = findViewById<EditText>(R.id.userName).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()
            val email = findViewById<EditText>(R.id.email).text.toString().trim()
            val name = findViewById<EditText>(R.id.fullName).text.toString().trim()
            val contact = findViewById<EditText>(R.id.contact).text.toString().trim()
            val category = "Mentor"
            val address = findViewById<EditText>(R.id.address).text.toString().trim()
            val confirmPassword = findViewById<EditText>(R.id.confirmPassword).text.toString().trim()

            if (userName.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() &&
                name.isNotEmpty() && contact.isNotEmpty() && address.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // Check if user already exists
                    if (!dbHelper.isUserExists(userName)) {
                        // Registration successful
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                        // Insert user data into the database
                        val imageData: ByteArray? = getImageDataFromImageView(profileImageView)
                        dbHelper.addUser(name, userName, password, email, contact, category, imageData, address)

                        val intent = Intent(this, LoginView::class.java)
                        startActivity(intent)
                    } else {
                        // User already exists
                        Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Passwords do not match
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Fields are empty
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        val chooseImageButton = findViewById<Button>(R.id.galleryAccessButton)
        chooseImageButton.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            // Set the selected image to ImageView
            profileImageView.setImageURI(selectedImageUri)
        }
    }

    private fun getImageDataFromImageView(imageView: ImageView): ByteArray? {
        val drawable = imageView.drawable as? BitmapDrawable ?: return null
        val bitmap = drawable.bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    companion object {
        private const val REQUEST_CODE_GALLERY = 101
    }
}
