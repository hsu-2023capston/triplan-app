package com.myosa.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentMainHomeBinding

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    override fun initView() {
        binding.apply {
            btnGroupJoin.tvGroupitemName.text="그룹 참여"

            btnGroupCreate.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_createGroupFragment)
            }

            btnGroupJoin.itemGroup.setOnClickListener{
                findNavController().navigate(R.id.action_mainHomeFragment_to_joinGroupFragment)
            }

            btnTest.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_groupHomeFragment)
            }
        }
    }



}