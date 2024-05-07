package com.capstone.passionventure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MentorView : AppCompatActivity() {
    private lateinit var mentorAdapter: MentorAdapter
    private lateinit var mentorList: List<Mentor>
    private lateinit var filteredList: MutableList<Mentor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_view)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        mentorList = listOf(
            Mentor(R.drawable.gii3, "Earl Andrino", "Agriculture"),
            Mentor(R.drawable.giii2, "Kevin Apiag", "Agriculture"),
            Mentor(R.drawable.giii1, "Joseph Villariasa", "Technology"),
            Mentor(R.drawable.random, "Hirai Momo", "Technology"),
            Mentor(R.drawable.random6, "Mina Sharon", "Marekting"),
            Mentor(R.drawable.random2, "Naruto Shipudden", "Marekting")
            // Add more mentors here as needed
        )

        filteredList = mentorList.toMutableList()

        mentorAdapter = MentorAdapter(this, filteredList)
        recyclerView.adapter = mentorAdapter

        val editText: EditText = findViewById(R.id.searchTab)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(text: String) {
        filteredList.clear()
        if (text.isEmpty()) {
            filteredList.addAll(mentorList)
        } else {
            val searchText = text.toLowerCase().trim()
            mentorList.forEach {
                if (it.category.toLowerCase().contains(searchText)) {
                    filteredList.add(it)
                }
            }
        }
        mentorAdapter.notifyDataSetChanged()
    }
}