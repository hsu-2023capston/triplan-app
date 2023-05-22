package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentCreateGroupBinding

class CreateGroupFragment : BaseFragment<FragmentCreateGroupBinding>(R.layout.fragment_create_group) {

    override fun initView() {
        binding.apply {
            btnCgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnCgCreate.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}