package com.capstone.triplan.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding
import com.capstone.triplan.presentation.adapter.GroupAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel

class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    private val viewModel : GroupHomeViewModel by viewModels()
    private val adapter = GroupAdapter { domainTrip -> findNavController()
    }

    override fun initView() {
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_tripHomeFragment)
            }
        }
    }


}