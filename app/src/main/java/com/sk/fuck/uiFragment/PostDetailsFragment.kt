package com.sk.fuck.uiFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sk.fuck.R
import com.sk.fuck.adapter.PostDetailsAdapter
import com.sk.fuck.databinding.FragmentPostDetailsBinding
import com.sk.fuck.model.PostDetailsModel
import com.sk.fuck.model.PostTagModel

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {
    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var postTagModel: PostTagModel
    private lateinit var postDetailsAdapter: PostDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostDetailsBinding.bind(view)

        postTagModel = ViewModelProvider(this)[PostTagModel::class.java]
        val tag = arguments?.getString("tag") ?: return

        postTagModel.getPost()

        postTagModel.postLiveData.observe(viewLifecycleOwner) { postDetails ->
            postDetails?.let {
                postDetailsAdapter = PostDetailsAdapter(it.posts)
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = postDetailsAdapter
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
