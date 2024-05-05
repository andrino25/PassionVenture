package com.capstone.passionventure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private var messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    // ViewHolder class
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileHolder: ImageView = itemView.findViewById(R.id.profileHolder)
        val personHolder: TextView = itemView.findViewById(R.id.personHolder)
        val currMessage1: TextView = itemView.findViewById(R.id.currMessage1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_list, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentItem = messageList[position]
        // Bind data to views
        holder.personHolder.text = currentItem.sender
        holder.currMessage1.text = currentItem.message
        holder.profileHolder.setImageResource(currentItem.imageResourceId)
    }

    override fun getItemCount() = messageList.size

    // Function to update the dataset
    fun updateList(newList: List<Message>) {
        messageList = newList
        notifyDataSetChanged()
    }
}

data class Message(
    val sender: String,
    val message: String,
    val imageResourceId: Int
)



