package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.capstone.domain.model.DomainUser
import com.capstone.triplan.databinding.ItemGroupMemberBinding

class GroupMemberAdapter : RecyclerView.Adapter<GroupMemberAdapter.GroupMemberViewHolder>() {
    private var items: List<DomainUser> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupMemberViewHolder {
        val binding = ItemGroupMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupMemberAdapter.GroupMemberViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GroupMemberViewHolder(private val binding: ItemGroupMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(member: DomainUser)
        {
            binding.apply {
                Glide.with(ivGroupMember)
                    .load("http://210.119.104.148:12345${member.default_id}")
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                    .into(ivGroupMember)
                tvGroupMember.text= member.user_name
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainUser>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}