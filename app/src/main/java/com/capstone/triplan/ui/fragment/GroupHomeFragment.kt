package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding

class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    override fun initView() {
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_tripHomeFragment)
            }
        }
    }


}