package com.myosa.triplan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentGroupHomeBinding

class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    override fun initView() {
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_tripHomeFragment)
            }
        }
    }


}