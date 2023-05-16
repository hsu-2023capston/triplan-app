package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentMainHomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    override fun initView() {
        signOut()
        loginCheck()
        binding.apply {
            btnGroupJoin.tvGroupitemName.text="그룹 참여"

            btnGroupCreate.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_createGroupFragment)
            }

            btnGroupJoin.itemGroup.setOnClickListener{
                findNavController().navigate(R.id.action_mainHomeFragment_to_joinGroupFragment)
            }

            btnTest.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_groupHomeFragment)
            }
        }
    }


    private fun signOut() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
    }

    private fun loginCheck(){
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            findNavController().navigate(R.id.action_mainHomeFragment_to_loginFragment)
        } else {//로그인 한 상태
            loge("로그인 O")
        }
    }

}