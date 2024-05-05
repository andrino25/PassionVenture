package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class HomePageMentorView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_mentor_view)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this, Edit1Profile::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }
            R.id.messages -> {
                val intent = Intent(this, Message1View::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }

            R.id.credentials -> {
                val intent = Intent(this, VerifyCredentials1::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }

            R.id.logout -> {

                val intent = Intent(this, LoginView::class.java)
                startActivity(intent)
                return true // Move the return statement inside each branch of when
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}