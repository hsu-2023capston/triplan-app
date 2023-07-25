package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding
import com.capstone.triplan.presentation.adapter.GroupMemberAdapter
import com.capstone.triplan.presentation.adapter.TripAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
// TODO: 그룹아이디를 받아와서 API 다시 호출 OR 그룹을 받아와서 그룹 설정 (백스택에서 문제가 생길 수 있지 않을까)

@AndroidEntryPoint
class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    private val viewModel: GroupHomeViewModel by activityViewModels()
    private val groupId = 40
    private val tripAdapter = TripAdapter { domainTrip ->
        findNavController().navigate(
            GroupHomeFragmentDirections.actionGroupHomeFragmentToTripHomeFragment(domainTrip.trip_id)
        )
    }
    private val groupMemberAdapter = GroupMemberAdapter()

    override fun initView() {
        binding.apply {
            rvGroupTrip.adapter= tripAdapter
           dwGroupHome.rvGroupMember.adapter= groupMemberAdapter
            viewModel.getTrip(groupId) // TODO: 그룹 아이디 하드 코딩됨
           viewModel.getGroupMember(groupId)
            tvTripMore.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_groupTripAllFragment)
            }
            tbGroupTrip.tbGroup.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnGroupDrawer.setOnClickListener {
                dlGroupHome.openDrawer(GravityCompat.END)
            }
            dwGroupHome.btGroupHomeDrawerSetting.setOnClickListener{
                Toast.makeText(context, "세팅", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_groupHomeFragment_to_editGroupFragment)
            }
            dwGroupHome.btGroupHomeDrawerOut.setOnClickListener{
                Toast.makeText(context, "그룹 나가기", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.trip.observe(viewLifecycleOwner)
        {
            val list  = it.reversed()
            if(it.size>=3)
                tripAdapter.setData(list.slice(0..2))
            else
                tripAdapter.setData(list)
        }
        viewModel.groupUsers.observe(viewLifecycleOwner)
        {
            groupMemberAdapter.setData(it)
        }
    }


}