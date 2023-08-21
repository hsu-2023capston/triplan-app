package com.capstone.triplan.ui.fragment

import android.util.DisplayMetrics
import android.view.Display
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripArchiveBinding
import com.capstone.triplan.presentation.adapter.MemoAdapter
import com.capstone.triplan.presentation.viewModel.TripArchiveViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TripArchiveFragment : BaseFragment<FragmentTripArchiveBinding>(R.layout.fragment_trip_archive) {
    private val viewModel: TripArchiveViewModel by viewModels()
    private lateinit var memoAdapter: MemoAdapter
    override fun initView() {
        binding.apply {
            memoAdapter = MemoAdapter()
            rvTaMemo.adapter = memoAdapter

            rvTaMemo.layoutManager = GridLayoutManager(context,3)

            cgTaCategory.setOnCheckedChangeListener{ group, checkedId ->
                when(checkedId){
                    R.id.c_ta_accommodation->
                        viewModel.memos.value?.let { memoAdapter.setData(it.filter { it.category == "숙소" }) }
                    R.id.c_ta_restaurant ->
                        viewModel.memos.value?.let { memoAdapter.setData(it.filter { it.category == "맛집" }) }
                    R.id.c_ta_cafe ->
                        viewModel.memos.value?.let { memoAdapter.setData(it.filter { it.category == "카페" }) }
                    R.id.c_ta_place ->
                        viewModel.memos.value?.let { memoAdapter.setData(it.filter { it.category == "장소" }) }
                    R.id.c_ta_etc ->
                        viewModel.memos.value?.let { memoAdapter.setData(it.filter { it.category == "기타" }) }
                    else ->
                        viewModel.memos.value?.let { memoAdapter.setData(it)}
                }
            }
        }
        setObserver()
    }

    private fun setObserver(){
        viewModel.trip.observe(viewLifecycleOwner){
            viewModel.getMemo(it.trip_id)
        }
        viewModel.memos.observe(viewLifecycleOwner){
            memoAdapter.setData(it)
            loge("${memoAdapter.itemCount}")
        }
    }
}