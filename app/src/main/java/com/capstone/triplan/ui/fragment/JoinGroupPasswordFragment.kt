package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentJoinGroupPasswordBinding

class JoinGroupPasswordFragment : BaseFragment<FragmentJoinGroupPasswordBinding>(R.layout.fragment_join_group_password) {
    override fun initView() {
        binding.apply {
            btnJgpBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        binding.apply {
            btnJgpJoin.setOnClickListener {
                findNavController().navigate(R.id.action_joinGroupPasswordFragment_to_mainHomeFragment)
            }
        }
    }


}