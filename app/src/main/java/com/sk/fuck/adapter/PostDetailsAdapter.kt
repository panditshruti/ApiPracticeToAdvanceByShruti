package com.sk.fuck.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sk.fuck.R
import com.sk.fuck.db.PostX

class PostDetailsAdapter (private val posts: List<PostX>) : RecyclerView.Adapter<PostDetailsAdapter.PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_details, parent, false)
            return PostViewHolder(view)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            val post = posts[position]
            holder.bind(post)


        }
        override fun getItemCount(): Int = posts.size

        class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val title: TextView = itemView.findViewById(R.id.postTitle)
            private val body: TextView = itemView.findViewById(R.id.postBody)
            private val tagsLayout: LinearLayout = itemView.findViewById(R.id.post_tags)
            private val views: TextView = itemView.findViewById(R.id.post_views)
            private val reactions: TextView = itemView.findViewById(R.id.post_reactions)
            private val user: TextView = itemView.findViewById(R.id.post_user)




            @SuppressLint("SetTextI18n")
            fun bind(post: PostX) {
                title.text = post.title
                body.text = post.body
                views.text = "Views: ${post.views}"
                reactions.text = "likes: ${post.reactions.likes} dislikes : ${post.reactions.dislikes}"
                user.text = "User ID: ${post.userId}"



                // Clear existing tags
                tagsLayout.removeAllViews()
                // Add tags
                for (tag in post.tags) {
                    val tagView = TextView(itemView.context).apply {
                        text = tag
                        setPadding(8, 4, 8, 4)
                        setBackgroundResource(R.drawable.tag_background)
                    }
                    tagsLayout.addView(tagView)
                }
//                user.setOnClickListener {
//                    userProfileListener.onUserProfileListener(post.userId.toString())
//                }
            }
        }
    }
