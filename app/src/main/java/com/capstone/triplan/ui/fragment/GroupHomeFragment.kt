package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentGroupHomeBinding
import com.capstone.triplan.presentation.adapter.TripAdapter
import com.capstone.triplan.presentation.viewModel.GroupHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupHomeFragment : BaseFragment<FragmentGroupHomeBinding>(R.layout.fragment_group_home) {
    private val viewModel: GroupHomeViewModel by viewModels()
    private val adapter = TripAdapter { domainTrip ->
        findNavController().navigate(
            GroupHomeFragmentDirections.actionGroupHomeFragmentToTripHomeFragment(domainTrip.trip_id)
        )
    }

    override fun initView() {
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_groupHomeFragment_to_tripHomeFragment)
            }
            rvGroupTrip.adapter= adapter
            viewModel.getTrip(1) // TODO: 그룹 아이디 하드 코딩됨

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.trip.observe(viewLifecycleOwner)
        {
            adapter.setData(it)
        }
    }


}