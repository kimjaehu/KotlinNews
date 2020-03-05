package com.example.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    fun fetchJson() {
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

