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

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase
): ViewModel() {
//    private var _groupList = MutableLiveData<List<DomainGroup>>()
//    val groupList = MutableLiveData<List<DomainGroup>>
//        get() = _groupList

    init {
        getGroupList()
    }

    fun getGroupList(){
        viewModelScope.launch {
            groupUseCase.getGroup(1)
        }
        Log.e("TAG", "getGroupList:", )
    }
}