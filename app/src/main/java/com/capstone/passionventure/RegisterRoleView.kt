package com.capstone.passionventure
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class RegisterRoleView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_role_view)

        val submit = findViewById<Button>(R.id.submitButton)
        val enthusiast = findViewById<RadioButton>(R.id.enthusiastRadioButton)
        val mentor = findViewById<RadioButton>(R.id.mentorRadioButton)

        submit.setOnClickListener {
            // Check which radio button is selected
            when {
                enthusiast.isChecked -> {
                    // Navigate to enthusiast page
                    val intent = Intent(this, EnthusiastRegisterView::class.java)
                    startActivity(intent)
                    mentor.isChecked = false
                }
                mentor.isChecked -> {
                    // Navigate to mentor page
                    val intent = Intent(this, MentorRegisterView::class.java)
                    startActivity(intent)
                    mentor.isChecked = false
                }
                else -> {
                    // Show a toast if no radio button is selected
                    Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}