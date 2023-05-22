package com.capstone.triplan.ui.fragment

import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentMainHomeBinding
import com.capstone.triplan.presentation.presentation.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference

    override fun initView() {
        auth = FirebaseAuth.getInstance()
        //signOut()
        addUserToFRDatabase(1,"forTest","TestName")
        //loginCheck()
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

    private fun addUserToFRDatabase(uid: Int, uToken: String, name: String){
        dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("user").child(uToken).setValue(User(uid,uToken,name))
        loge("hi")
    }

    private fun signOut() {
        auth.signOut()
        GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
    }

    private fun loginCheck(){
        if (auth.currentUser == null) {
            findNavController().navigate(R.id.action_mainHomeFragment_to_loginFragment)
        } else {//로그인 한 상태
            loge("로그인 O")
        }
    }

}