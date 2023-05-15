package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentJoinGroupBinding


class JoinGroupFragment : BaseFragment<FragmentJoinGroupBinding>(R.layout.fragment_join_group) {
    override fun initView() {
        binding.apply {
            btnJgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnJgJoin.setOnClickListener {
                findNavController().navigate(R.id.action_joinGroupFragment_to_joinGroupPasswordFragment)
            }
        }


    }

}