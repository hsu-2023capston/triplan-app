package com.capstone.triplan.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.usecase.GroupUseCase
import com.capstone.domain.usecase.TripUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase,
    private val tripUseCase: TripUseCase,
    private val prefs: Prefs
): ViewModel() {
    private var _groupList : MutableLiveData<List<DomainGroup>> = MutableLiveData()
    val groupList : LiveData<List<DomainGroup>>
        get() = _groupList

    private var _onGoingTripList: MutableLiveData<List<DomainTrip>> = MutableLiveData()
    val onGoingTripList : LiveData<List<DomainTrip>>
        get() = _onGoingTripList

    private var _plannedTrip: MutableLiveData<List<DomainTrip>> = MutableLiveData()
    val plannedTrip: LiveData<List<DomainTrip>>
        get() = _plannedTrip

    fun getGroupList(uid: Int){
        viewModelScope.launch {
            val data = groupUseCase.getGroup(uid)
            Log.e("TAG,", "getGroupList: ${data}", )
            _groupList.postValue(data)
            Log.e("TAG", "_: ${_groupList.value}", )
            Log.e("TAG", "getGroupList: ${groupList.value}", )

        }
    }

    fun getTripList(uid:Int){
        viewModelScope.launch {
            val data = tripUseCase.getTripHome(uid)
            Log.e("TAG", "getTripList: $data", )
            val now = LocalDate.now()
            val onGoing = data.filter { it ->
                val startDate = LocalDate.parse(it.start_date,DateTimeFormatter.ofPattern("yyyy.MM.dd."))
                now.isAfter(startDate) }
            val planned = data.filter { it->
                val startDate = LocalDate.parse(it.start_date,DateTimeFormatter.ofPattern("yyyy.MM.dd."))
                startDate.isAfter(now)
            }
            _onGoingTripList.postValue(onGoing)
            _plannedTrip.postValue(planned)
            Log.e("TAG", "onGoing: $onGoing", )
            Log.e("TAG", "planned: $planned", )

        }
    }

    fun setTrip(trip: DomainTrip)
    {
        viewModelScope.launch {
            prefs.trip = GsonBuilder().create().toJson(trip)
        }
    }
}