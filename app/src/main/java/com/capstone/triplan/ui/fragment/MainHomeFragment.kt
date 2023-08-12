package com.capstone.triplan.ui.fragment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.capstone.domain.model.DomainTrip
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentMainHomeBinding
import com.capstone.triplan.di.CommonUtil.setProfileImage
import com.capstone.triplan.presentation.adapter.GroupAdapter
import com.capstone.triplan.presentation.adapter.GroupTripAdapter
import com.capstone.triplan.presentation.presentation.User
import com.capstone.triplan.presentation.viewModel.MainHomeViewModel
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
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
    private val onGoingTripAdapter = GroupTripAdapter{
        mainHomeViewModel.setTrip(it)
        findNavController().navigate(R.id.action_mainHomeFragment_to_bottom_navigation)
    }
    private val plannedTripAdapter = GroupTripAdapter{
        mainHomeViewModel.setTrip(it)
        findNavController().navigate(R.id.action_mainHomeFragment_to_bottom_navigation)
    }

    override fun initView() {
        auth = FirebaseAuth.getInstance()
        //signOut()
        //addUserToFRDatabase(1,"forTest","TestName")
        loginCheck()
        setObserver()
        binding.apply {
            btnGroupJoin.tvGroupitemName.text="그룹 참여"
            rvMhOngoing.adapter = onGoingTripAdapter
            rvMhPlanned.adapter = plannedTripAdapter
            rvMhGroup.isNestedScrollingEnabled = false
            groupAdapter = GroupAdapter()//.apply { setHasStableIds(true) }
            groupAdapter.onClick = {
                val action = MainHomeFragmentDirections.actionMainHomeFragmentToGroupHomeFragment(it)
                findNavController().navigate(action)
            }
            rvMhGroup.adapter = groupAdapter

            btnGroupCreate.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomeFragment_to_createGroupFragment)
            }

            btnGroupJoin.itemGroup.setOnClickListener{
                findNavController().navigate(R.id.action_mainHomeFragment_to_joinGroupFragment)
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
        loge("로그아웃 됐어야함!?")
        findNavController().navigate(R.id.action_mainHomeFragment_to_loginFragment)
    }

    private fun revokeAccess(){
        auth.currentUser?.delete()
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
                tvMhMsg.text = it.user_name?.let { it1 -> String.format(requireContext().getString(R.string.mainhome_msg),it.user_name,it.trip_cnt) }
                Glide.with(ivMhProfile)
//                    .load("http://210.119.104.148:12345/image${it.default?.default_path}")
                    .load(it.default_id?.let { it1 -> setProfileImage(it1) })
                    .circleCrop()
                    .into(ivMhProfile)
                tvMhGroupmsg.text = it.user_name?.let { it1-> String.format(requireContext().getString(R.string.mainhome_group),it.user_name) }

            }
            it.user_id?.let { it1 ->
                mainHomeViewModel.getGroupList(it1)
                mainHomeViewModel.getTripList(it1)
            }
        }
        mainModel.isNew.observe(viewLifecycleOwner){
            if(it==1) {
                signOut()
                loge("로그아웃 할게.~?")
                loge("DB에 없음 메인홈")
            }
            else
                loge("DB에 있음")
        }
        mainHomeViewModel.groupList.observe(viewLifecycleOwner){
            loge("$it")
            groupAdapter.setData(it)
            //binding.rvMhGroup.minimumWidth = (155*groupAdapter.itemCount)+10
            loge("${groupAdapter.itemCount}")
        }
        mainHomeViewModel.plannedTrip.observe(viewLifecycleOwner){
            plannedTripAdapter.setData(it)
            loge("플랜드: ${plannedTripAdapter.itemCount}")
        }
        mainHomeViewModel.onGoingTripList.observe(viewLifecycleOwner){
            onGoingTripAdapter.setData(it)
            loge("온고잉: ${onGoingTripAdapter.itemCount}")
        }
    }
}