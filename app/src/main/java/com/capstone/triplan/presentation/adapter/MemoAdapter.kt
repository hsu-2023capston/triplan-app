package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainMemo
import com.capstone.triplan.databinding.ItemMemoBinding

class MemoAdapter(): RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {
    private var items: List<DomainMemo> = ArrayList()

    inner class MemoViewHolder(private val binding: ItemMemoBinding): RecyclerView.ViewHolder(binding.root){
        fun setContent(memo: DomainMemo){
            binding.apply {
                tvImUrl.text = memo.content
                if(memo.is_url==1){
                    Glide.with(ivImImg)
                        .load(memo.image_path)
                        .into(ivImImg)
                }
                tvImLike.text = memo.like_count.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoAdapter.MemoViewHolder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.setContent(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainMemo>){
        items = newItems
        notifyDataSetChanged()
    }
}