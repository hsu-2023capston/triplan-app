package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.domain.model.DomainGroup
import com.capstone.triplan.databinding.ItemGroupBinding
import com.capstone.triplan.di.CommonUtil

class GroupAdapter(): RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {
    private var items: List<DomainGroup> = ArrayList()
    lateinit var onClick: (DomainGroup) -> Unit

    inner class GroupViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setContent(group: DomainGroup){
            binding.apply {
                tvIgName.text = group.group_name
                Glide.with(ivIgImage)
                    .load("http://210.119.104.148:12345${group.group_path}")
                    .into(ivIgImage)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): GroupAdapter.GroupViewHolder {
        val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupAdapter.GroupViewHolder, position: Int) {
        holder.setContent(items[position])
        holder.itemView.setOnClickListener {
            onClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainGroup>){
        items = newItems
        notifyDataSetChanged()
    }


}