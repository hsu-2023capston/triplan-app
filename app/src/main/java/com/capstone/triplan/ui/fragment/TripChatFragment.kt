package com.capstone.triplan.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.data.remote.dataSource.ChatMessageEntity
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripChatBinding
import com.capstone.triplan.di.CommonUtil.getTime
import com.capstone.triplan.presentation.adapter.ChatAdapter
import com.capstone.triplan.presentation.viewModel.TripChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripChatFragment : BaseFragment<FragmentTripChatBinding>(R.layout.fragment_trip_chat) {
    private val chatViewModel: TripChatViewModel by viewModels()
    private lateinit var  adapter : ChatAdapter

    override fun initView() {
        chatViewModel.getMessagesForTrip(1)
        binding.apply {
            adapter = ChatAdapter().apply { setHasStableIds(true) }
            rvTcChat.adapter = adapter
            btnTcSend.setOnClickListener {
                chatViewModel.sendMessage(ChatMessageEntity(1,1,"test",etTcMessage.text.toString(),getTime(System.currentTimeMillis())))
                etTcMessage.setText("")
                loge("전송함")
            }
        }
        setObserve()
    }

    fun setObserve(){
        chatViewModel.chatLiveData.observe(viewLifecycleOwner){
            adapter.setData(it)
            loge("변화감지${it.value}")
            binding.rvTcChat.scrollToPosition(adapter.itemCount -1)
        }
    }

}