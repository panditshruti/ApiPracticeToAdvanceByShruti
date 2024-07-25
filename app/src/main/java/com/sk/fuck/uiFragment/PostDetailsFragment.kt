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

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {
    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var postDetailsModel: PostDetailsModel
    private lateinit var postDetailsAdapter: PostDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPostDetailsBinding.bind(view)

        postDetailsModel = ViewModelProvider(this)[PostDetailsModel::class.java]
        postDetailsModel.getPostDetails(arguments?.getString("tag")!!)

        postDetailsModel.postDetailsLiveData.observe(viewLifecycleOwner) {

            if (it != null) {
                postDetailsAdapter = PostDetailsAdapter(it.posts)
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = postDetailsAdapter
            }

        }

    }
//
//    override fun onUserProfileListener(id: String) {
//        val action = UrlPostTagFragmentDirections.actionUrlPostTagFragmentToUserProfileFragment(id)
//        findNavController().navigate(action)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
