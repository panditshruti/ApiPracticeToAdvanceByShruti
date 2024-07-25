package com.sk.fuck.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sk.fuck.ApiService
import com.sk.fuck.db.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailsModel :ViewModel(){
        var postDetailsLiveData = MutableLiveData<Post?>()


        fun getPostDetails(tag:String){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/posts/tag/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.getPostDetails(tag)

            call.enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                    if (response.isSuccessful){
                        val postDetails = response.body()
                        postDetailsLiveData .value = postDetails


                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("MyApi", "Request failed", t)
                }


            })

        }

    }
