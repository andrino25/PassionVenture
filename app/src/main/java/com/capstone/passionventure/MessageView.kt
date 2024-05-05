package com.capstone.passionventure

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageView : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var messageList: List<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_view)

        val backToDashboard = findViewById<Button>(R.id.BtnBack)
        val searchTab = findViewById<EditText>(R.id.searchTab)
        recyclerView = findViewById(R.id.recyclerView)

        // Generate initial message list
        messageList = generateMessageList()

        // Set up RecyclerView
        adapter = MessageAdapter(messageList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Handle back button click
        backToDashboard.setOnClickListener {
            val intent = Intent(this, HomePageEnthusiastView::class.java)
            startActivity(intent)
        }

        // Listen for changes in the search input
        searchTab.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter messages based on search input for sender's name
                val filteredList = messageList.filter {
                    it.sender.contains(s.toString(), ignoreCase = true)
                }
                adapter.updateList(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun generateMessageList(): List<Message> {
        val messageList = mutableListOf<Message>()
        // Add your custom messages and names here
        messageList.add(Message("John Wick", "Hello, how are you?", R.drawable.random))
        messageList.add(Message("Alice Borderland", "I'm doing great, thanks!", R.drawable.random1))
        messageList.add(Message("Bob Aaron", "Can we meet tomorrow?", R.drawable.random2))
        messageList.add(Message("Emily Barbon", "Sure, what time works for you?", R.drawable.random3))
        messageList.add(Message("Hirai Momo", "How was your day?", R.drawable.random4))
        messageList.add(Message("Sharon Mina", "This is a testing message", R.drawable.random5))
        messageList.add(Message("James Baldwin", "James unsent a message.", R.drawable.random6))
        // Add more messages as needed
        return messageList
    }

}

