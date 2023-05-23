package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.usecase.GroupUseCase
import com.capstone.domain.usecase.TripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupHomeViewModel @Inject constructor(
    private val tripUseCase: TripUseCase
) : ViewModel() {
    private var _trip: MutableLiveData<List<DomainTrip>> = MutableLiveData<List<DomainTrip>>()
    val trip: LiveData<List<DomainTrip>>
        get() = _trip
    fun getTrip(group_id:Int)
    {
        viewModelScope.launch {
            _trip.value = tripUseCase.getTrip(group_id)
        }
    }
}