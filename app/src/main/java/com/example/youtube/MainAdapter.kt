package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val videoTitles = listOf("First", "Second", "3rd", "4rd")

    override fun getItemCount(): Int {
        return videoTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_row, parent, false)
        val viewHolder = CustomViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val videoTitle = videoTitles[position]
        holder.itemView.findViewById<TextView>(R.id.textView_video_title).text = videoTitle
    }
}

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}