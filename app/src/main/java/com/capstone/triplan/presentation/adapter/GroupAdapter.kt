package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.capstone.domain.model.DomainTrip
import com.capstone.triplan.databinding.ItemTripTogoBinding

class GroupAdapter(val onclick : (DomainTrip) -> Unit) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {
    private var items: List<DomainTrip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = ItemTripTogoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  GroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.setItem(items[position])
    }
    inner class GroupViewHolder(val binding: ItemTripTogoBinding):RecyclerView.ViewHolder(binding.root) {
        fun setItem(trip: DomainTrip)
        {
            binding.tvGroupTripName.text = trip.trip_name
            binding.tvGroupTripStartDate.text = trip.start_date
            binding.tvGroupTripEndDate.text = trip.end_date
            Glide.with(binding.ivGroupTrip)
                .load(trip.trip_path)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(binding.ivGroupTrip)
            binding.root.setOnClickListener {
                onclick(trip)
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainTrip>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}