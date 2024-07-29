package com.sk.fuck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sk.fuck.databinding.ItemPostDetailsBinding
import com.sk.fuck.db.PostX

class PostDetailsAdapter(private val posts: List<PostX>) : RecyclerView.Adapter<PostDetailsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ItemPostDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostX) {
            binding.postTitle.text = post.title // Assuming you have a title field in your PostX class
            binding.postBody.text = post.body
            binding.postUser.text = post.userId.toString()
            binding.postViews.text = post.views.toString()
            binding.postReactions.text = post.reactions.toString()
            binding.postTag.text = post.tags.joinToString(", ") // Assuming tags is a list
        }
    }
}
