package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentEditGroupBinding


class EditGroupFragment : BaseFragment<FragmentEditGroupBinding>(R.layout.fragment_edit_group) {
    override fun initView() {
        binding.apply {
            btnEgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

}