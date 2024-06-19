package com.example.youtube

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.databinding.CourseLessonRowBinding
import com.example.youtube.model.CourseLesson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        rvMain = binding.recyclerViewMain
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)

        val navBarTile = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTile

        fetchJson()
    }

    private fun fetchJson() {

        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId

        val request = Request.Builder().url(courseDetailUrl).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val courseLesson = gson.fromJson(body, Array<CourseLesson>::class.java)

                runOnUiThread {
                    binding.recyclerViewMain.adapter = CourseDetailAdapter(courseLesson)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    }

    private class CourseDetailAdapter(val courseLesson: Array<CourseLesson>) : RecyclerView.Adapter<CourseLessonViewHolder>() {

        override fun getItemCount(): Int {
            return courseLesson.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)
            return CourseLessonViewHolder(CourseLessonRowBinding.bind(customView))
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
            val courseLesson = courseLesson[position]

            holder.binding.textViewCourseLessonTitle.text = courseLesson.name
            holder.binding.textViewDuration.text = courseLesson.duration

            val imageView = holder.binding.imageViewCourseLessonThumbnail
            Picasso.get().load(courseLesson.imageUrl).into(imageView)

            holder.courseLesson = courseLesson


        }


    }

    class CourseLessonViewHolder(val binding: CourseLessonRowBinding, var courseLesson: CourseLesson? = null) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            const val COURSE_LINK_KEY = "COURSE_LINK"
        }

        init {
            binding.root.setOnClickListener {
                println("Attemp to load webview somehow")

                val intent = Intent(binding.root.context, CourseLessonActivity::class.java)

                intent.putExtra(COURSE_LINK_KEY, courseLesson?.link)

                binding.root.context.startActivity(intent)
            }
        }


    }