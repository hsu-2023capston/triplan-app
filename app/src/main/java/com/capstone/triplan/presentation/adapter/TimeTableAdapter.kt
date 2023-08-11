package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.domain.model.DomainTimeTable
import com.capstone.triplan.databinding.ItemTripScheduleBinding
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeTableAdapter : RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {
    private var items : List<DomainTimeTable> = ArrayList()
    inner class TimeTableViewHolder(val binding: ItemTripScheduleBinding):RecyclerView.ViewHolder(binding.root) {
        fun setItem(timeTable: DomainTimeTable){
            binding.apply {
                tvTrpScheduleCount.text = (items.indexOf(timeTable) + 1).toString()
                tvTrpScheduleTitle.text = timeTable.title
                tvTripScheduleStartTime.text = timeTable.start_time
                tvTripScheduleEndTime.text = timeTable.end_time
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding = ItemTripScheduleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainTimeTable>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}