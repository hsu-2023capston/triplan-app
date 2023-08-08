package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupTripAllBinding
import com.capstone.triplan.presentation.adapter.GroupTripAdapter
import com.capstone.triplan.presentation.viewModel.GroupTripAllViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupTripAllFragment : BaseFragment<FragmentGroupTripAllBinding>(R.layout.fragment_group_trip_all) {
    private val viewModel : GroupTripAllViewModel by viewModels()
    private val args by navArgs<GroupTripAllFragmentArgs>()
    private val adapter = GroupTripAdapter { domainTrip ->
        viewModel.setTrip(domainTrip)
        findNavController().navigate(R.id.action_groupTripAllFragment_to_bottom_navigation)
    }
    override fun initView() {
        binding.apply {
            tbGroupTripAll.tbGroup.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            rvGroupTripAll.adapter = adapter
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setData(args.trip.toList())
    }

}