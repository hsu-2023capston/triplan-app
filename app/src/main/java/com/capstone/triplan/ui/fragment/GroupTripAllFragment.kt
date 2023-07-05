package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupTripAllBinding
import com.capstone.triplan.presentation.adapter.TripAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel


class GroupTripAllFragment : BaseFragment<FragmentGroupTripAllBinding>(R.layout.fragment_group_trip_all) {
    private val args by navArgs<GroupTripAllFragmentArgs>()
    private val viewModel: GroupHomeViewModel by activityViewModels()
    private val adapter = TripAdapter { domainTrip ->
        findNavController().navigate(
            GroupHomeFragmentDirections.actionGroupHomeFragmentToTripHomeFragment(domainTrip.trip_id)
        )
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
        viewModel.trip.observe(viewLifecycleOwner)
        {
            adapter.setData(it.reversed())
        }
    }

}