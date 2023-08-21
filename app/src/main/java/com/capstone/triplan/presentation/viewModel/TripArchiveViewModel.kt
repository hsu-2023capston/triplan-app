package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainMemo
import com.capstone.domain.model.DomainTrip
import com.capstone.domain.usecase.MemoUseCase
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripArchiveViewModel @Inject constructor(
    private val memoUseCase: MemoUseCase,
    private val prefs: Prefs
): ViewModel() {
    private var _memos: MutableLiveData<List<DomainMemo>> = MutableLiveData()

    val memos: LiveData<List<DomainMemo>>
        get() = _memos

    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData()
    val trip: LiveData<DomainTrip>
        get() = _trip

    init {
        viewModelScope.launch {
            _trip.value= GsonBuilder().create().fromJson(prefs.trip, DomainTrip::class.java)
        }
    }
    fun getMemo(trip_id:Int) {
        viewModelScope.launch {
            _memos.value = memoUseCase.getMemo(trip_id)

        }
    }


}