package com.capstone.triplan.presentation.adapter

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.data.remote.dataSource.ChatMessageEntity
import com.capstone.domain.model.DomainGroup
import com.capstone.triplan.databinding.ItemTripChatMessageBinding
import com.capstone.triplan.di.CommonUtil
import com.capstone.triplan.di.CommonUtil.createHyperlink
import com.capstone.triplan.di.CommonUtil.isURL
import com.capstone.triplan.di.CommonUtil.setProfileImage

class ChatAdapter(val user_id: Int): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private var items : List<ChatMessageEntity> = ArrayList()
    lateinit var onClick: (ChatMessageEntity) -> Unit
    //  private lateinit var items : MutableLiveData<List<ChatMessageEntity>>

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatViewHolder, position: Int) {
        holder.setContent(items[position])
        if(items[position].content?.let { isURL(it) } == true){
            holder.itemView.setOnClickListener {
                onClick(items[position])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        val binding = ItemTripChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    inner class ChatViewHolder(private val binding: ItemTripChatMessageBinding): RecyclerView.ViewHolder(binding.root){
        fun setContent(message : ChatMessageEntity){
            binding.apply {
                if(message.uid == user_id){
                    tvMychatContent.visibility = View.VISIBLE
                    tvMychatTime.visibility = View.VISIBLE
                    tvOtherchatContent.visibility = View.GONE
                    tvMychatTime.visibility=View.GONE
                    if(isURL(message.content!!))
                        tvMychatContent.text = Html.fromHtml(createHyperlink(message.content!!),Html.FROM_HTML_MODE_COMPACT)
                    else
                        tvMychatContent.text = message.content
                    tvMychatTime.text = message.timestamp!!.slice(11..15)
                }
                else{
                    tvMychatContent.visibility = View.GONE
                    tvMychatTime.visibility = View.GONE
                    tvOtherchatContent.visibility = View.VISIBLE
                    tvOtherchatContent.text = message.content
                    tvOtherchatName.text = message.name
                    Glide.with(ivOtherchatImg)
//                    .load("http://210.119.104.148:12345/image${it.default?.default_path}")
                        .load(message!!.img?.let { setProfileImage(it) })
                        .circleCrop()
                        .into(ivOtherchatImg)
                }
            }
        }
    }

    fun setData(messages: MutableLiveData<List<ChatMessageEntity>>){
        val list = messages.value
        if (list != null) {
            this.items = list
        }
        Log.e("TAG", "setData: 잘 받았지용 ${items}")
        notifyDataSetChanged()
    }

}

