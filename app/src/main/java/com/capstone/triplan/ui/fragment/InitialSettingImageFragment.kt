package com.capstone.triplan.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentInitialSettingImageBinding
import com.capstone.triplan.di.CommonUtil.setProfileImage
import com.capstone.triplan.presentation.viewModel.InitialSettingViewModel


class InitialSettingImageFragment : BaseFragment<FragmentInitialSettingImageBinding>(R.layout.fragment_initial_setting_image) {

    private val isModel : InitialSettingViewModel by activityViewModels()

    override fun initView() {
        setObserver()
        binding.apply {
            ivIsp01.setOnClickListener {
                isModel.setProfile(1)
            }
            ivIsp02.setOnClickListener {
                isModel.setProfile(2)
            }
            ivIsp03.setOnClickListener {
                isModel.setProfile(3)
            }
            ivIsp04.setOnClickListener {
                isModel.setProfile(4)
            }

            btnIsiBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnIsiStart.setOnClickListener {
                isModel.postUser()
                findNavController().navigate(R.id.action_initialSettingImageFragment_to_mainHomeFragment)
            }
        }
    }

    fun setObserver() {
        isModel.pid.observe(viewLifecycleOwner){
            binding.apply {
                ivIspProfile.setImageResource(setProfileImage(it))
            }
        }
        isModel.name.observe(viewLifecycleOwner){
            binding.apply {
                //tvIspMsg.text = String.format(requireContext().getString(R.string.initial_setting_text_profile),it))

            }
        }
    }
}
