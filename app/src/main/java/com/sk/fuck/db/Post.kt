package com.sk.fuck.db

data class Post(
    val limit: Int,
    val posts: List<PostX>,
    val skip: Int,
    val total: Int
)