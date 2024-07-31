package com.sk.fuck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sk.fuck.click.PostTagClickListner
import com.sk.fuck.databinding.ItemPostBinding
import com.sk.fuck.db.PostX

class PostAdapter(private val posts: List<PostX>, private val listener: PostTagClickListner) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostX) {
            binding.postTitle.text = post.tags.toString()

            binding.postTitle.setOnClickListener {
                listener.onPostTagClick(post.tags.toString(), post.body) // Assuming PostX has an idLink property
            }
        }
    }
}
