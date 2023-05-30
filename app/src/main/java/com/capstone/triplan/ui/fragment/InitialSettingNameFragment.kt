package com.capstone.triplan.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentInitialSettingNameBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.presentation.viewModel.InitialSettingViewModel
import com.capstone.triplan.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialSettingNameFragment : BaseFragment<FragmentInitialSettingNameBinding>(R.layout.fragment_initial_setting_name) {

    private val isModel : InitialSettingViewModel by activityViewModels()

    override fun initView() {
        binding.apply {
            etIsnName.setText(isModel.name.value)
            btnIsnFront.setOnClickListener {
                isModel.setName(etIsnName.text.toString())
                findNavController().navigate(R.id.action_initialSettingNameFragment_to_initialSettingImageFragment)
            }
        }
    }

}