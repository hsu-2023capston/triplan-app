package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.capstone.domain.model.DomainTrip
import com.capstone.triplan.R
import com.capstone.triplan.databinding.ItemTripTogoBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class TripAdapter(val onclick: (DomainTrip) -> Unit) :
    RecyclerView.Adapter<TripAdapter.GroupViewHolder>() {
    private var items: List<DomainTrip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding =
            ItemTripTogoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class GroupViewHolder(val binding: ItemTripTogoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun setItem(trip: DomainTrip) {
            val endDate = LocalDate.parse(trip.end_date)
            val startDate = LocalDate.parse(trip.start_date)
            val now = LocalDate.now()
            binding.apply {
                tvGroupTripName.text = trip.trip_name
                tvGroupTripStartDate.text = trip.start_date
                tvGroupTripEndDate.text = trip.end_date
                Glide.with(ivGroupTrip)
                    .load("http://210.119.104.148:12345${trip.trip_path}")
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .into(ivGroupTrip)
                root.setOnClickListener {
                    onclick(trip)
                }
                if (now.isAfter(endDate)) {
                    clTicket.setBackgroundResource(R.drawable.bg_trip_done)
                    ivTripStamp.visibility = View.VISIBLE
                    tvDayBefore.visibility = View.GONE
                }
                if(ChronoUnit.DAYS.between(startDate,now).toInt() == 0)
                    tvDayBefore.text = "D-Day"
                else
                    tvDayBefore.text = "D"+ChronoUnit.DAYS.between(startDate,now).toString()

            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainTrip>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}