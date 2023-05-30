package com.capstone.triplan.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.usecase.GroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase
): ViewModel() {
    private var _groupList : MutableLiveData<List<DomainGroup>> = MutableLiveData()
    val groupList : LiveData<List<DomainGroup>>
        get() = _groupList

    fun getGroupList(uid: Int){
        viewModelScope.launch {
            val data = groupUseCase.getGroup(uid)
            Log.e("TAG,", "getGroupList: ${data}", )
            _groupList.postValue(data)
            Log.e("TAG", "_: ${_groupList.value}", )
            Log.e("TAG", "getGroupList: ${groupList.value}", )

        }
    }
}