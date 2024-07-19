package com.sk.fuck.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sk.fuck.ApiService
import com.sk.fuck.db.Post
import com.sk.fuck.db.PostX
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostTagModel:ViewModel() {


    private val _postLiveData = MutableLiveData<Post?>()
    val postLiveData: LiveData<Post?> get() = _postLiveData

    private val apiService: ApiService

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPost() {
        val call = apiService.getPostTag()

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val postTag = response.body()
                    _postLiveData.value = postTag
                    Log.d("MYApi", postTag.toString())
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("MYApi", "Request failed", t)
            }
        })
    }

}
