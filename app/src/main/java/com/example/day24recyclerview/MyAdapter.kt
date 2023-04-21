package com.example.day24recyclerview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView.OnItemClickListener
import android.widget.ShareActionProvider
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var newsArrayList: ArrayList<News>,var context: Activity):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //3 important functions or topics of recycler view
    // to create new view instance
    //when layout manager fails to find a suitable view for each item
    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int,):MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView,mylistener)   //part 2 passing mylistener we created.
        //creating the view in this twolines
    }


    //populate items with data before and after listview.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsArrayList[position]//asigning the array of oth element
        holder.hTitle.text = currentItem.newsHeading//holding
        holder.hImage.setImageResource(currentItem.newsImage)

    }

    // returns the size of the array
    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    //it holds the view so views are not created everytime , so memory can be saved
    //like a man standing with a plate for his food
    class MyViewHolder (itemView : View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView) { //part 2 passing listener
        val hTitle = itemView.findViewById<TextView>(R.id.tVHeading)
        val hImage = itemView.findViewById<ShapeableImageView>(R.id.headingImage)

        //creating init for part 2
        //calling the function when we click on it
        init{
            itemView.setOnClickListener{   //setonclicklistener is built in method
                listener.onItemClicking(adapterPosition)
            }
        }

    }


    //recycler part 2
    //step1
    private lateinit var mylistener: onItemClickListener    //2.var name is mylistener and type interface that is onItemClickListener

    interface onItemClickListener{                          //1. interface name is onItemClickListener
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){ //3.function name is setItemClickListener
        mylistener = listener                                //assigning value
    }





}