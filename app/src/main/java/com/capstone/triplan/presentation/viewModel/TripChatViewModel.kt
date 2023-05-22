package com.capstone.triplan.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.data.remote.dataSource.ChatMessageEntity
import com.google.firebase.database.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TripChatViewModel @Inject constructor(
): ViewModel() {

    private val messageRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("messages")

    private var _chatLiveData = MutableLiveData<MutableLiveData<List<ChatMessageEntity>>>()
    val chatLiveData: MutableLiveData<MutableLiveData<List<ChatMessageEntity>>>
        get() =  _chatLiveData

    fun sendMessage(message: ChatMessageEntity) {
        message.tid?.let { messageRef.child(it.toString()).push().setValue(message) }
    }

    fun getMessagesForTrip(tripId: Int): MutableLiveData<List<ChatMessageEntity>> {
        val messagesLiveData = MutableLiveData<List<ChatMessageEntity>>()

        messageRef.child(tripId.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages = mutableListOf<ChatMessageEntity>()
                for (messageSnapshot in dataSnapshot.children) {
                    val message = messageSnapshot.getValue(ChatMessageEntity::class.java)
                    message?.let {
                        messages.add(it)
                    }
                }
                Log.e("TAG", "onDataChange: ${messages}", )
                messagesLiveData.value = messages
                chatLiveData.postValue(messagesLiveData)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 오류 처리 로직을 추가할 수 있습니다.
            }
        })
        return messagesLiveData
    }

}