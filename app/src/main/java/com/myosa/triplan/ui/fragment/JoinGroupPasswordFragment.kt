package com.myosa.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentJoinGroupPasswordBinding

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