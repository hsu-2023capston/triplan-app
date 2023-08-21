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
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TripHomeViewModel @Inject constructor(
    private val tripUseCase: TripUseCase,
    private val timeTableUseCase: TimeTableUseCase,
    private val prefs: Prefs
) : ViewModel() {
    private var _trip: MutableLiveData<DomainTrip> = MutableLiveData() //data 설정하는거
    val trip: LiveData<DomainTrip> // getter 함수 느낌, 변수 x -> 변화를 감지 애서 _trip의 value를 리턴, 가져오는 함수
        get() = _trip

    private var _timeTable: MutableLiveData<List<Pair<String, List<DomainTimeTable>>>?> =
        MutableLiveData()
    val timeTable: LiveData<List<Pair<String, List<DomainTimeTable>>>?>
        get() = _timeTable

    private var _tripUser: MutableLiveData<List<DomainUser>> = MutableLiveData()
    val tripUser: LiveData<List<DomainUser>>
        get() = _tripUser

    private var _timeTableDate: MutableLiveData<List<String>> = MutableLiveData()
    val timeTableDate: LiveData<List<String>>
        get() = _timeTableDate


    fun getTripUser(trip_id: Int) {
        viewModelScope.launch {
            _tripUser.value = tripUseCase.getTripMember(trip_id)
        }
    }

    //푸) 이거랑 아래 함수를 통해 데이터를 가져오는건 좋지만 두개를 옵져버로 걸지말고
    //
    private fun getTripTimeTable(trip_id: Int) {
        viewModelScope.launch {
            _timeTable.value = timeTableUseCase.getTripTimeTable(trip_id)
        }

    }


    private fun getTimeTableDate(trip_id: Int) {
        viewModelScope.launch {
            _timeTableDate.value = timeTableUseCase.getTimeTableDate(trip_id)
        }
    }

    //코루틴 익셉션 핸들러
    //코루틴 슈퍼바이저 잡
    //서버 반환값이 단일화 대응 함수
    //databinding 간단한기능을 viewModel
    //레트릿결과를 call 객체, response 객체
    //
//    fun checkDoubleData(){
//        if(timeTable.value.isNullOrEmpty() && timeTable.value.isNullOrEmpty())
//            dsafd
//        else{
//
//        }
//    }

    private fun getSharedPreference() {
        viewModelScope.launch {
            _trip.value = GsonBuilder().create().fromJson(prefs.trip, DomainTrip::class.java)
        }
    }


    //푸) 이 뷰모델이 처음 생성됐을 때 호출하는것보다 이 기능을 쓰는 Fragment에서 oncreate 타이밍때 함수 호출시키는게 어떨까?
    // 속도는 비슷할것같은데 더 알아보기 쉬울지도
    init {
        getSharedPreference()
        trip.value?.trip_id?.let {
            getTripTimeTable(it)
            getTimeTableDate(it)
        }

    }

}