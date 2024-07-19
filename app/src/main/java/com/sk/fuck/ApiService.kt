package com.sk.fuck

import com.sk.fuck.db.Post
import com.sk.fuck.db.PostX
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPostTag(): Call<Post>

}
