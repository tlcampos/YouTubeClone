package com.example.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ActivityMainBinding

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        rvMain = binding.recyclerViewMain
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMain.adapter = CourseDetailAdapter()

        val navBarTile = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTile
    }

    private class CourseDetailAdapter : RecyclerView.Adapter<CourseLessonViewHolder>() {

        override fun getItemCount(): Int {
            return 8
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {

        }


    }

    class CourseLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}