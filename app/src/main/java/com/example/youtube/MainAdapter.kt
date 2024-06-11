package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_row, parent, false)
        val viewHolder = CustomViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos[position]
        holder.itemView.findViewById<TextView>(R.id.textView_video_title).text = video.name
        holder.itemView.findViewById<TextView>(R.id.textView_channel_name).text = video.channel.name + " - " + "20K views\n4 days ago"

        val thumbnailImageView = holder.itemView.findViewById<ImageView>(R.id.imageView_video_thumbnail)
        Picasso.get().load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder.itemView.findViewById<ImageView>(R.id.imageView_chanel_profile)
        Picasso.get().load(video.channel.profileimageUrl).into(channelProfileImageView)

    }
}

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)