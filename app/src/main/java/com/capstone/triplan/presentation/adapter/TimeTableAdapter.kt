package com.capstone.triplan.presentation.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.capstone.domain.model.DomainTimeTable
import com.capstone.triplan.R
import com.capstone.triplan.databinding.ItemTripScheduleBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeTableAdapter : RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {
    private var items: List<DomainTimeTable> = ArrayList()
    private val nowTime = LocalTime.now()
    private val nowDate = LocalDate.now()

    inner class TimeTableViewHolder(val binding: ItemTripScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(timeTable: DomainTimeTable) {
            binding.apply {
                val start_time = LocalTime.parse(timeTable.start_time, DateTimeFormatter.ofPattern("HH:mm"))
                val end_time = LocalTime.parse(timeTable.end_time, DateTimeFormatter.ofPattern("HH:mm"))
                val start_date = LocalDate.parse(timeTable.start_date, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                tvTripScheduleCount.text = (items.indexOf(timeTable) + 1).toString()
                tvTripScheduleTitle.text = timeTable.title
                tvTripScheduleStartTime.text = timeTable.start_time
                tvTripScheduleEndTime.text = timeTable.end_time
                if(nowDate.isEqual(start_date)){
                    if (nowTime.isAfter(start_time) && nowTime.isBefore(end_time)) {
                        cvTripScheduleBackground.strokeColor = Color.parseColor("#FF087FCB")
                        cvTripScheduleCount.setCardBackgroundColor(Color.parseColor("#FF087FCB"))
                    }
                }
                else
                {
                    cvTripScheduleBackground.strokeColor = Color.parseColor("#FF8F8F8F")
                    cvTripScheduleCount.setCardBackgroundColor(Color.parseColor("#FF8F8F8F"))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding =
            ItemTripScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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