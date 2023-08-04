package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.usecase.TripUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TripHomeViewModel @Inject constructor(
    private val tripUseCase: TripUseCase,
    private val prefs: Prefs
) : ViewModel() {
    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData()
    val trip: LiveData<DomainTrip>
        get() = _trip
    init {
        viewModelScope.launch {
            _trip.value= GsonBuilder().create().fromJson(prefs.trip,DomainTrip::class.java)
        }
    }
}