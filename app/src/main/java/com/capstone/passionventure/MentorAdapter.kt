package com.capstone.passionventure

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MentorAdapter(private val context: Context, private val mentorList: List<Mentor>) :
    RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mentors_list, parent, false)
        return MentorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        val mentor = mentorList[position]
        holder.bind(mentor)
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }

    inner class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageButton)
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val categoryTextView: TextView = itemView.findViewById(R.id.category)

        fun bind(mentor: Mentor) {
            imageView.setImageResource(mentor.imageResource)
            nameTextView.text = mentor.name
            categoryTextView.text = mentor.category
        }
    }

}

data class Mentor(val imageResource: Int, val name: String, val category: String)

