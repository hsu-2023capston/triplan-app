package com.myosa.triplan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentJoinGroupBinding


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