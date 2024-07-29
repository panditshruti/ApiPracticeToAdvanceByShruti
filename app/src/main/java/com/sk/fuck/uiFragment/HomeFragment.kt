package com.sk.fuck.uiFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sk.fuck.PostTagClickListner
import com.sk.fuck.R
import com.sk.fuck.adapter.PostAdapter
import com.sk.fuck.databinding.FragmentHomeBinding
import com.sk.fuck.model.PostTagModel

class HomeFragment : Fragment(R.layout.fragment_home), PostTagClickListner {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var postTagModel: PostTagModel
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        Toast.makeText(requireContext(), "this", Toast.LENGTH_SHORT).show()
        postTagModel = ViewModelProvider(this)[PostTagModel::class.java]
        postTagModel.getPost()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        postTagModel.postLiveData.observe(viewLifecycleOwner) { post ->
            if (post != null) {
                postAdapter = PostAdapter(post.posts, this)
                binding.recyclerView.adapter = postAdapter
            } else {
                Toast.makeText(requireContext(), "No posts available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostTagClick(tag: String, idLink: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(idLink, tag)
        findNavController().navigate(action)
    }
}
