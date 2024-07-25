package com.sk.fuck

import com.sk.fuck.db.Post
import com.sk.fuck.db.PostX
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun getPostTag(): Call<Post>

    @GET("{tag}")
    fun getPostDetails(@Path("tag") tag:String):Call<Post>
}
