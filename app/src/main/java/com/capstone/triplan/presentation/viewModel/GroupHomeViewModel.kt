package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.model.DomainUser
import com.capstone.domain.usecase.GroupUseCase
import com.capstone.domain.usecase.TripUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupHomeViewModel @Inject constructor(
    private val tripUseCase: TripUseCase,
    private val groupUseCase: GroupUseCase,
    private val prefs: Prefs
) : ViewModel() {
    private var _trips: MutableLiveData<List<DomainTrip>> = MutableLiveData()
    val trips: LiveData<List<DomainTrip>>
        get() = _trips

    private var _groupUsers: MutableLiveData<List<DomainUser>> = MutableLiveData()
    val  groupUsers : LiveData<List<DomainUser>>
        get() = _groupUsers
    fun getTrip(group_id:Int)
    {
        viewModelScope.launch {
            _trips.value = tripUseCase.getTrip(group_id)
        }
    }
    fun getGroupMember(group_id: Int){
        viewModelScope.launch {
            _groupUsers.value = groupUseCase.getGroupMember(group_id)
        }
    }
    fun setTrip(trip: DomainTrip)
    {
        viewModelScope.launch {
            prefs.trip = GsonBuilder().create().toJson(trip)
        }
    }
}