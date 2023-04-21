package com.example.day24recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        supportActionBar?.hide()

        //recieving details from mainactivity

        val heading = intent.getStringExtra("heading")
        val imageId = intent.getIntExtra("ImageId",R.drawable.img1)
        val news = intent.getStringExtra("news")

        val headingTV = findViewById<TextView>(R.id.newsHeading)
        val headingIV = findViewById<ImageView>(R.id.newsImage)
        val newstv = findViewById<TextView>(R.id.newsContent)

        headingTV.text = heading
        newstv.text =news
        headingIV.setImageResource(imageId)



    }
}