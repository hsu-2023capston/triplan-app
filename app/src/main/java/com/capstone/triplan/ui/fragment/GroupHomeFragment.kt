package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding
import com.capstone.triplan.presentation.adapter.GroupMemberAdapter
import com.capstone.triplan.presentation.adapter.TripAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: 그룹아이디를 받아와서 API 다시 호출 OR 그룹을 받아와서 그룹 설정 (백스택에서 문제가 생길 수 있지 않을까)
// TODO: 그룹 아이디가 아니라 그룹내용 전제츷 줘야한다.

@AndroidEntryPoint
class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    private val viewModel: GroupHomeViewModel by viewModels()
    private val groupId = 40
    private val tripAdapter = TripAdapter { domainTrip ->
        findNavController().navigate(
            GroupHomeFragmentDirections.actionGroupHomeFragmentToBottomNavigation(domainTrip)
        )
        viewModel.setTrip(domainTrip)
    }
    private val groupMemberAdapter = GroupMemberAdapter()

    override fun initView() {
        binding.apply {
            rvGroupTrip.adapter = tripAdapter
            dwGroupHome.rvGroupMember.adapter = groupMemberAdapter
            viewModel.getTrip(groupId) // TODO: 그룹 아이디 하드 코딩됨
            viewModel.getGroupMember(groupId)
            viewModel.trips.observe(viewLifecycleOwner){ trip->
                tvTripMore.setOnClickListener {
                    findNavController().navigate(GroupHomeFragmentDirections.actionGroupHomeFragmentToGroupTripAllFragment(trip.toTypedArray()))
                }
            }
            tbGroupTrip.tbGroup.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnGroupDrawer.setOnClickListener {
                dlGroupHome.openDrawer(GravityCompat.END)
            }
            dwGroupHome.btGroupHomeDrawerSetting.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_editGroupFragment)
            }
            dwGroupHome.btGroupHomeDrawerOut.setOnClickListener {
                Toast.makeText(context, "그룹 나가기", Toast.LENGTH_SHORT).show()
            }
//            Glide.with(ivGroupHome)
//                .load("http://210.119.104.148:12345${group.group_path}")
//                .into(ivGroupHome)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.trips.observe(viewLifecycleOwner)
        {
            if (it.size >= 3)
                tripAdapter.setData(it.slice(0..2))
            else
                tripAdapter.setData(it)
        }
        viewModel.groupUsers.observe(viewLifecycleOwner)
        {
            groupMemberAdapter.setData(it)
        }
    }


}