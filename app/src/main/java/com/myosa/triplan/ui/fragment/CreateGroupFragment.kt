package com.myosa.triplan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentCreateGroupBinding

class CreateGroupFragment : BaseFragment<FragmentCreateGroupBinding>(R.layout.fragment_create_group) {

    override fun initView() {
        binding.apply {
            btnCgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}