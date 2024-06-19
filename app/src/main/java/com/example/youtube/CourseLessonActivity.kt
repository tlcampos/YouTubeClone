package com.example.youtube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube.databinding.ActivityCourseLessonBinding

class CourseLessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseLessonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCourseLessonBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val courseLink = intent.getStringExtra("COURSE_LINK").toString()

        binding.webviewCourseLesson.settings.javaScriptEnabled = true
        binding.webviewCourseLesson.settings.loadWithOverviewMode = true
        binding.webviewCourseLesson.settings.useWideViewPort = true

        binding.webviewCourseLesson.loadUrl(courseLink)
    }
}