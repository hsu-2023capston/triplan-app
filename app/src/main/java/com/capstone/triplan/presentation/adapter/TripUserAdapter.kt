package com.capstone.triplan.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.domain.model.DomainUser
import com.capstone.triplan.databinding.ItemTripMainBinding
import com.capstone.triplan.databinding.ItemTripTogoBinding

class TripUserAdapter: RecyclerView.Adapter<TripUserAdapter.UserViewHolder>() {
    private var items: List<DomainUser> = ArrayList()
    inner class UserViewHolder(val binding: ItemTripMainBinding) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripUserAdapter.UserViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GroupTripAdapter.TripViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}