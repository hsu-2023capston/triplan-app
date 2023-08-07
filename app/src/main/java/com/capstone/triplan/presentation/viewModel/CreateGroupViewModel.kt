package com.capstone.triplan.presentation.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.domain.usecase.GroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase
): ViewModel()
{
    private val _message: MutableLiveData<String> = MutableLiveData()

    val message: MutableLiveData<String>
        get() = _message
    fun postGroup(group_name: String, group_pw: String, user_id: Int, group_path: File,){
        viewModelScope.launch {
            message.postValue(groupUseCase.postGroup(group_name,group_pw,user_id,group_path).Message)
            Log.e("TAG", "postGroup: ${message.value}", )
        }
    }

}