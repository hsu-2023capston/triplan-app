package com.capstone.triplan.ui.fragment

import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentInitialSettingNameBinding
import com.google.firebase.auth.FirebaseAuth


class InitialSettingNameFragment : BaseFragment<FragmentInitialSettingNameBinding>(R.layout.fragment_initial_setting_name) {

    private lateinit var auth : FirebaseAuth

    override fun initView() {
        auth = FirebaseAuth.getInstance()
        binding.apply {
            //etIsnName.text = auth.
        }
    }

}