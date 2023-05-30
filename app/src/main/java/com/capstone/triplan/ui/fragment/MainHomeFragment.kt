package com.capstone.triplan.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.BuildConfig
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentMainHomeBinding
import com.capstone.triplan.di.CommonUtil.setProfileImage
import com.capstone.triplan.presentation.adapter.GroupAdapter
import com.capstone.triplan.presentation.presentation.User
import com.capstone.triplan.presentation.viewModel.MainHomeViewModel
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    private val mainModel : MainViewModel by activityViewModels()
    private val mainHomeViewModel : MainHomeViewModel by viewModels()
    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    private lateinit var groupAdapter: GroupAdapter

    override fun initView() {
        auth = FirebaseAuth.getInstance()
        //signOut()
        //addUserToFRDatabase(1,"forTest","TestName")
        loginCheck()
        setObserver()
        binding.apply {
            btnGroupJoin.tvGroupitemName.text="그룹 참여"

            groupAdapter = GroupAdapter().apply { setHasStableIds(true) }
            rvMhGroup.adapter = groupAdapter

            btnGroupCreate.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_createGroupFragment)
            }

            btnGroupJoin.itemGroup.setOnClickListener{
                findNavController().navigate(R.id.action_mainHomeFragment_to_joinGroupFragment)
            }

            btnTest.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_groupHomeFragment)
            }

            button3.setOnClickListener {
                signOut()
            }
        }
    }

    private fun addUserToFRDatabase(uid: Int, uToken: String, name: String){
        dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("user").child(uToken).setValue(User(uid,uToken,name))
        loge("hi")
    }

    private fun signOut() {
        mainModel.signOut()
        auth.signOut()
        GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
        findNavController().navigate(R.id.action_mainHomeFragment_to_loginFragment)
    }

    private fun loginCheck(){
        if (auth.currentUser == null) {
//            val user = auth.currentUser
//            val provideData = user!!.providerData
//            user.uid
            findNavController().navigate(R.id.action_mainHomeFragment_to_loginFragment)
        } else {//로그인 한 상태
            mainModel.getUserLogin(auth.currentUser!!.uid)
            //mainModel.getUserLogin("token")
        }
    }

    private fun setObserver(){
        mainModel.user.observe(viewLifecycleOwner){
            binding.apply {
                tvMhMsg.text = it.user_name?.let { it1 -> String.format(requireContext().getString(R.string.mainhome_msg),it.user_name,3) }
                Glide.with(ivMhProfile)
//                    .load("http://210.119.104.148:12345/image${it.default?.default_path}")
                    .load(it.default_id?.let { it1 -> setProfileImage(it1) })
                    .circleCrop()
                    .into(ivMhProfile)
                tvMhGroupmsg.text = it.user_name?.let { it1-> String.format(requireContext().getString(R.string.mainhome_group),it.user_name) }
            }
            it.user_id?.let { it1 -> mainHomeViewModel.getGroupList(it1) }
        }
        mainHomeViewModel.groupList.observe(viewLifecycleOwner){
            loge("$it")
            groupAdapter.setData(it)
        }
    }
}