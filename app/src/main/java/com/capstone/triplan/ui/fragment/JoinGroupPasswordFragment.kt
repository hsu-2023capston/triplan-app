package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentJoinGroupPasswordBinding

class JoinGroupPasswordFragment : BaseFragment<FragmentJoinGroupPasswordBinding>(R.layout.fragment_join_group_password) {
    private val args: JoinGroupPasswordFragmentArgs by navArgs()
    override fun initView() {
        binding.apply {
            tvJgpName.text = args.groupName
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