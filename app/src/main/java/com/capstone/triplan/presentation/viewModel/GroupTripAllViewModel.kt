package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainTrip
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupTripAllViewModel @Inject constructor(
    private val prefs: Prefs
) : ViewModel()  {
    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData()
    val trip: LiveData<DomainTrip>
        get() = _trip
    fun setTrip(trip: DomainTrip)
    {
        viewModelScope.launch {
            prefs.trip = GsonBuilder().create().toJson(trip)
        }
    }
}