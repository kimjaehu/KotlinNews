package com.example.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    fun fetchJson() {
//        okhttp - to be changed to retrofit
//        val url = "https://www.reddit.com/r/kotlin/.json"
//        val request = Request.Builder().url(url).build()
//
//        val client = OkHttpClient()
//
//        client.newCall(request).enqueue(object: Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                println("failed to fetch")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body?.string()
//                println(body)
//
//                val gson = GsonBuilder().create()
//
//                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
//
//                runOnUiThread {
//                    recyclerView_main.adapter = MainAdapter(homeFeed)
//                }
//            }
//
//        })

//        retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchKotlinNews().enqueue(object: Callback<HomeFeed> {
            override fun onResponse(call: Call<HomeFeed>, response: Response<HomeFeed>) {
                val homeFeed = response.body()
                    runOnUiThread {    recyclerView_main.adapter = MainAdapter(homeFeed!!)
               }
            }

            override fun onFailure(call: Call<HomeFeed>, t: Throwable) {
                println("failed to retrieve response")
            }
        })
    }
}

