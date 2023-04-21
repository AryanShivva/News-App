package com.example.day24recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView :RecyclerView
    lateinit var newsArrayList: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        myRecyclerView=findViewById(R.id.recyclerView)

        val newsImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
        )

        val newsHeadingArray = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issues of BBC tax searches with EAM Jaishankar",
            "Cooking gas price hiked by 50 Rupees for domestic, 350 Rupees for commercial cylinders.",
            "Joe Biden appoints two prominent Indian-American corporate leaders to his Expert Council",
            "Sergery Lavrov will raise suspected bombing of Nord stream II at 620: Russian Foreign Ministry",
            "Belarusian Leader Lukshenko visits China and Ukraine tensions",
            "China rips new U.S house committee on countering Beiging",

        )

        //creating another array for storing the news
        val newsContents = arrayOf(
            getString(R.string.news_content),
            getString(R.string.car),
            getString(R.string.bike),
            getString(R.string.aero),
            getString(R.string.heli),
            getString(R.string.apple),
        )

        //to set bhav items inside recycler view,vertically scrolling or horizentally scrolling,uniform grid etc

        myRecyclerView.layoutManager=LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for(index in newsImageArray.indices){
            //assigning to arrays to one array that is news array
            val news = News(newsHeadingArray[index],newsImageArray[index],newsContents[index])//part 2 passing array to asssign values
            newsArrayList.add(news)
        }

        //creating adapter.
        //myRecyclerView.adapter = MyAdapter(newsArrayList,this)


        //part 2
        var myAdapter = MyAdapter(newsArrayList,this)
        myRecyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object :MyAdapter.onItemClickListener{
            //on clicking each item you want to perform
            override fun onItemClicking(position: Int) {
                val intent = Intent(this@MainActivity,NewsDetailActivity::class.java)
                intent.putExtra("heading",newsArrayList[position].newsHeading)
                intent.putExtra("ImageId",newsArrayList[position].newsImage)
                intent.putExtra("news",newsArrayList[position].newsContents)
                startActivity(intent)
            }
        })

    }
}