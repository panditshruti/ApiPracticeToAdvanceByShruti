package com.sk.fuck.uiFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sk.fuck.R
import com.sk.fuck.databinding.FragmentPostDetailsBinding

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {
    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPostDetailsBinding.bind(view)

        val idLink = arguments?.getString("idLink")
        val tag = arguments?.getString("tag")

        binding.t1.text = idLink
        binding.t2.text = tag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
