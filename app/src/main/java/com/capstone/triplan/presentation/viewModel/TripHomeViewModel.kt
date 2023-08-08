package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainTimeTable
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser
import com.capstone.domain.usecase.TimeTableUseCase
import com.capstone.domain.usecase.TripUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TripHomeViewModel @Inject constructor(
    private val tripUseCase: TripUseCase,
    private val timeTableUseCase: TimeTableUseCase,
    private val prefs: Prefs
) : ViewModel() {
    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData() //data 설정하는거
    val trip: LiveData<DomainTrip> // getter 함수 느낌 -> 변화를 감지 애서 _trip의 value를 리턴
        get() = _trip

    private var _timeTable: MutableLiveData<List<DomainTimeTable>> = MutableLiveData()

    val timeTable: LiveData<List<DomainTimeTable>>
        get() = _timeTable

    private var _tripUser: MutableLiveData<List<DomainUser>> = MutableLiveData()
    val tripUser: LiveData<List<DomainUser>>
        get() = _tripUser

    fun getTripUser(trip_id: Int){
        viewModelScope.launch {
            _tripUser.value = tripUseCase.getTripMember(trip_id)
        }
    }

    fun getTripTimeTable(trip_id: Int) {
        viewModelScope.launch {
            _timeTable.value = timeTableUseCase.getTripTimeTable(trip_id)
        }
    }

    init {
        viewModelScope.launch {
            _trip.value= GsonBuilder().create().fromJson(prefs.trip,DomainTrip::class.java)
        }
    }
}