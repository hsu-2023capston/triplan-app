package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainTimeTable
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.usecase.TimeTableUseCase
import com.capstone.domain.usecase.TripUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TripScheduleViewModel @Inject constructor(
    private val timeTableUseCase: TimeTableUseCase,
    private val prefs: Prefs
) : ViewModel()  {
    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData() //data 설정하는거
    val trip: LiveData<DomainTrip> // getter 함수 느낌, 변수 x -> 변화를 감지 애서 _trip의 value를 리턴, 가져오는 함수
        get() = _trip

    private var _timeTable: MutableLiveData<List<Pair<String, List<DomainTimeTable>>>?> =
        MutableLiveData()
    val timeTable: LiveData<List<Pair<String, List<DomainTimeTable>>>?>
        get() = _timeTable

    private var _timeTableDate: MutableLiveData<List<String>> = MutableLiveData()
    val timeTableDate: LiveData<List<String>>
        get() = _timeTableDate

    private fun getSharedPreference() {
        viewModelScope.launch {
            _trip.value = GsonBuilder().create().fromJson(prefs.trip, DomainTrip::class.java)
        }
    }

    private fun getTripTimeTable() {
        viewModelScope.launch {
            _timeTable.value = timeTableUseCase.getTripTimeTable(GsonBuilder().create().fromJson(prefs.trip, DomainTrip::class.java).trip_id)
        }

    }

    private fun getTimeTableDate() {
        viewModelScope.launch {
            _timeTableDate.value = timeTableUseCase.getTimeTableDate(GsonBuilder().create().fromJson(prefs.trip, DomainTrip::class.java).trip_id)
        }
    }
    init {
        getSharedPreference()
        getTimeTableDate()
        getTripTimeTable()
    }
}