# News-App

Created a News App using Recycler View

<img width="356" alt="Screenshot 2023-05-02 at 10 54 13 PM" src="https://user-images.githubusercontent.com/124916476/235739906-ead55c57-00c7-40cd-94cf-e9204d318127.png">.      <img width="364" alt="Screenshot 2023-05-02 at 10 56 12 PM" src="https://user-images.githubusercontent.com/124916476/235739931-7b38f7f5-4990-417b-86d9-43635a6cd7d7.png">



RECYCLER VIEW IN KOTLIN : 

In this article, you will know how to implement RecyclerView in Android using Kotlin. Before moving further let us know about RecyclerView. A RecyclerView is an advanced version of ListView with improved performance. When you have a long list of items to show you can use RecyclerView. It has the ability to reuse its views. In RecyclerView when the View goes out of the screen or not visible to the user it won’t destroy it, it will reuse these views. This feature helps in reducing power consumption and providing more responsiveness to the application. Now let’s see how to implement RecyclerView using Kotlin.


Step by Step Implementation
Step 1: Create a New Project

On the Welcome screen of Android Studio, click on Create New Project. If you have a project already opened, Go to File > New > New Project. Then select a Project Template window, select Empty Activity and click Next. Enter your App Name in the Name field. Select Kotlin from the Language drop-down menu.


Step 2: Add the Dependencies

Go to app < Gradle Scripts < gradle.build(Module: app) and add the following dependencies.

           dependencies{
              // for adding recyclerview
              implementation 'androidx.recyclerview:recyclerview:1.2.0'

              // for adding cardview
              implementation 'androidx.cardview:cardview:1.0.0'
            }
      
Step 3: Go to activity_main.xml and add the following code

Add RecyclerView to activity_main.xml you can add it from the drag and drop from the design section or you can add it manually by writing some initial characters of RecyclerView then the IDE will give you suggestions for RecyclerView then select RecyclerView it will automatically add it to your layout file.

        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout 
          xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:orientation="vertical"
          tools:context=".MainActivity">

          <androidx.recyclerview.widget.RecyclerView
              android:id="@+id/recyclerview"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              tools:itemCount="5"
              tools:listitem="@layout/card_view_design" />

        </LinearLayout>
      
      
Step 4: Create a New Layout Resource File

Now create a new Layout Resource File which will be used to design our CardView layout. Go to app > res > layout > right-click on layout > New > Layout Resource File and name that file as card_view_design and add the code provided below. In this file, you can design the layout to show it into the RecyclerView.

        <?xml version="1.0" encoding="utf-8"?>
        <androidx.cardview.widget.CardView 
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_margin="10dp"
            app:cardElevation="6dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:padding="5dp">

                <ImageView
                    android:id="@+id/imageview"
                    android:layout_width="40dp"
                    android:layout_height="40dp" />

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="10dp"
                    android:layout_marginLeft="15dp"
                    android:text="Item"
                    android:textSize="20sp"
                    android:textStyle="bold" />

            </LinearLayout>
  
        </androidx.cardview.widget.CardView>
        
        
Step 5: Create a new Kotlin class


Go to app > java > package name > right-click > New > Kotlin class/file and choose Data class from the list. Name that file as ItemsViewModel and then click on OK. This file will hold the information of every item which you want to show in your RecyclerView.

data class ItemsViewModel(val image: Int, val text: String) {
}


Step 6: Create Adapter Class 

Go to app > java > package name > right-click > New > Kotlin class/file and name that file as CustomAdapter and then click on OK. After this add the code provided below. Comments are added inside the code to understand the code in more detail.

This class contains some important functions to work with the RecyclerView these are as follows:

onCreateViewHolder(): This function sets the views to display the items.
onBindViewHolder(): This function is used to bind the list items to our widgets such as TextView, ImageView, etc.
getItemCount(): It returns the count of items present in the list.


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
        
        
Step 7: Working with the MainActivity.kt

Go to the MainActivity.kt file and refer to the following code. Below is the code for the MainActivity.kt file. Comments are added inside the code to understand the code in more detail.

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
