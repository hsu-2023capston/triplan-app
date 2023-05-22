package com.capstone.triplan.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.capstone.data.remote.dataSource.ChatMessageEntity
import com.capstone.triplan.databinding.ItemTripChatMessageBinding

class ChatAdapter(): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private var items : List<ChatMessageEntity> = ArrayList()
  //  private lateinit var items : MutableLiveData<List<ChatMessageEntity>>

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatViewHolder, position: Int) {
        holder.setContent(items[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        val binding = ItemTripChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    inner class ChatViewHolder(private val binding: ItemTripChatMessageBinding): RecyclerView.ViewHolder(binding.root){
        fun setContent(message : ChatMessageEntity){
            binding.tvContent.text = message.content
        }
    }

    fun setData(messages: MutableLiveData<List<ChatMessageEntity>>){
        val list = messages.value
        if (list != null) {
            this.items = list
        }
        Log.e("TAG", "setData: 잘 받았지용 ${items}", )
        notifyDataSetChanged()
    }

}