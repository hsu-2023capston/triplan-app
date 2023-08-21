package com.capstone.triplan.ui.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.data.remote.dataSource.ChatMessageEntity
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripChatBinding
import com.capstone.triplan.di.CommonUtil.getTime
import com.capstone.triplan.di.CommonUtil.isURL
import com.capstone.triplan.presentation.adapter.ChatAdapter
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.capstone.triplan.presentation.viewModel.TripChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL

@AndroidEntryPoint
class TripChatFragment : BaseFragment<FragmentTripChatBinding>(R.layout.fragment_trip_chat) {
    private val chatViewModel: TripChatViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var  adapter : ChatAdapter
    private var first = false

    override fun initView() {
        chatViewModel.trip.value?.trip_id?.let { chatViewModel.getMessagesForTrip(it) }
        binding.apply {
            adapter = ChatAdapter(mainViewModel.user.value!!.user_id!!).apply { setHasStableIds(true) }
            adapter.onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.content))
                requireContext().startActivity(intent)
            }
            rvTcChat.adapter = adapter
            btnTcSend.setOnClickListener {
                val user = mainViewModel.user.value
                chatViewModel.sendMessage(ChatMessageEntity(chatViewModel.trip.value?.trip_id,user!!.user_id,user!!.user_name,user!!.default_id,etTcMessage.text.toString(),getTime(System.currentTimeMillis())))
//                다른사람 채팅 테스트용
//                chatViewModel.sendMessage(ChatMessageEntity(chatViewModel.trip.value?.trip_id,71,"집게",3,etTcMessage.text.toString(),getTime(System.currentTimeMillis())))
                if(isURL(etTcMessage.text.toString())){
                    loge("url임")
                    chatViewModel.postUrl(mainViewModel.user.value!!.user_id!!,etTcMessage.text.toString())
                }
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
            if(!first){
                binding.rvTcChat.scrollToPosition(adapter.itemCount -1)
                first = true
            }
        }

    }




}