package com.capstone.triplan.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentJoinGroupBinding
import com.capstone.triplan.presentation.viewModel.JoinGroupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinGroupFragment : BaseFragment<FragmentJoinGroupBinding>(R.layout.fragment_join_group) {
    private val joinGroupModel: JoinGroupViewModel by viewModels()

    override fun initView() {
        binding.apply {
            btnJgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnJgJoin.setOnClickListener {
                joinGroupModel.getGroupName(etJgCode.text.toString())
            }
        }
        observer()
    }

    fun observer(){
        joinGroupModel.groupName.observe(viewLifecycleOwner){
            val action = JoinGroupFragmentDirections.actionJoinGroupFragmentToJoinGroupPasswordFragment(it)
            findNavController().navigate(action)

        }
    }

}