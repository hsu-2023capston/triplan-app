package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.domain.model.DomainGroup
import com.capstone.domain.model.DomainGroupName
import com.capstone.domain.usecase.GroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinGroupViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase
): ViewModel(){
    private var _groupName : MutableLiveData<String> = MutableLiveData()

    val groupName : LiveData<String>
        get() = _groupName

    fun getGroupName(code:String){
        viewModelScope.launch {
            val data = groupUseCase.getGroupName(code)
            if(data.Message=="성공"&&data.Data!=null){
                _groupName.postValue(data.Data)
            }
        }
    }
}