package com.example.codepathand101proj6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var list: MutableList<Triple<String,String,String>>
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.picRV)
        list = mutableListOf()
        
        getAPOD()
    }

    private fun getAPOD() {
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=${getString(R.string.api_key)}&count=10", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Image", "response successful$json")
                val resultsJSON = json.jsonArray
                for (i in 0 until resultsJSON.length()) {
                    val jsonObj = resultsJSON.getJSONObject(i)
                    val date = jsonObj.getString("date")
                    val description = jsonObj.getString("explanation")
                    val imageUrl = jsonObj.getString("url")
                    list.add(Triple(date, imageUrl, description))
                }
                val adapter = RVAdapter(list)
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Image Error", errorResponse)
            }
        }]
    }
}