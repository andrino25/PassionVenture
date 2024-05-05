package com.capstone.passionventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Message1View : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var messageList: List<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message1_view)

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
        messageList.add(Message("Tom", "Hey, just checking in! How's everything going?", R.drawable.gii3))
        messageList.add(Message("Lily", "Hey, want to grab some lunch later?", R.drawable.giii1))
        messageList.add(Message("Sam", "Did you watch the game last night? It was epic!", R.drawable.giii2))
        messageList.add(Message("Alex", "What's up? Got any plans for the weekend?", R.drawable.pfimage1_removebg_preview))
        messageList.add(Message("Emily", "Hey, did you hear about the new café downtown?", R.drawable.pfimage2_removebg_preview))
        messageList.add(Message("Chris", "I'm bored, let's do something fun!", R.drawable.pfimage3_removebg_preview))
        messageList.add(Message("Jessica", "Just wanted to say hi! How's your day going?", R.drawable.pfimage4_removebg_preview))

        // Add more messages as needed
        return messageList
    }

}