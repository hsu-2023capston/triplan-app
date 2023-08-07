package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser
import com.capstone.triplan.databinding.ItemTripMainUserBinding
import com.capstone.triplan.di.CommonUtil

class TripUserAdapter : RecyclerView.Adapter<TripUserAdapter.UserViewHolder>() {
    private var items: List<DomainUser> = ArrayList()

    inner class UserViewHolder(val binding: ItemTripMainUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(user: DomainUser) {
            binding.apply {
                tvTripUser.text = user.user_name
                Glide.with(ivTripUserProfile)
//                    .load("http://210.119.104.148:12345/image${it.default?.default_path}")
                    .load(user.default_id?.let { it1 -> CommonUtil.setProfileImage(it1)
                    })
                    .circleCrop()
                    .into(ivTripUserProfile)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripUserAdapter.UserViewHolder {
        val binding =
            ItemTripMainUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainUser>) {
        this.items = newItems
        notifyDataSetChanged()
    }

}