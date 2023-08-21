package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding
import com.capstone.triplan.presentation.adapter.GroupMemberAdapter
import com.capstone.triplan.presentation.adapter.GroupTripAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    private val groupHomeViewModel: GroupHomeViewModel by viewModels()
    private val args : GroupHomeFragmentArgs by navArgs()
    private val groupTripAdapter = GroupTripAdapter { domainTrip ->
        groupHomeViewModel.setTrip(domainTrip)
        findNavController().navigate(R.id.action_groupHomeFragment_to_bottomNavigation)
    }
    private val groupMemberAdapter = GroupMemberAdapter()

    override fun initView() {
        binding.apply {
            rvGroupTrip.adapter = groupTripAdapter
            dwGroupHome.rvGroupMember.adapter = groupMemberAdapter
            groupHomeViewModel.getTrip(args.group.group_id)
            groupHomeViewModel.getGroupMember(args.group.group_id)
            groupHomeViewModel.trips.observe(viewLifecycleOwner){ trip->
                tvTripMore.setOnClickListener {
                    findNavController().navigate(GroupHomeFragmentDirections.actionGroupHomeFragmentToGroupTripAllFragment(trip.toTypedArray(),args.group))
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
            dwGroupHome.tvGroupCode.text = args.group.group_code
            tbGroupTrip.tvTripTitle.text = args.group.group_name
            tvRecentTrip.text = String.format(getString(R.string.grouphome_msg),args.group.group_name)
            Glide.with(ivGroupHome)
                .load("http://210.119.104.148:12345${args.group.group_path}")
                .into(ivGroupHome)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupHomeViewModel.trips.observe(viewLifecycleOwner)
        {
            if (it.size >= 3)
                groupTripAdapter.setData(it.slice(0..2))
            else
                groupTripAdapter.setData(it)
        }
        groupHomeViewModel.groupUsers.observe(viewLifecycleOwner)
        {
            groupMemberAdapter.setData(it)
        }
    }


}