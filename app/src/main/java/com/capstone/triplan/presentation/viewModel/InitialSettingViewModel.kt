package com.capstone.triplan.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainUser
import com.capstone.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialSettingViewModel @Inject constructor(
    prefs: Prefs,
    private val userUseCase: UserUseCase
): ViewModel() {
    private val _user: MutableLiveData<DomainUser> = MutableLiveData()
    val user: MutableLiveData<DomainUser>
        get() = _user

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String>
        get() = _name

    private val _token: MutableLiveData<String> = MutableLiveData()
    val token: MutableLiveData<String>
        get() = _token

    private val _pid: MutableLiveData<Int> = MutableLiveData(0)
    val pid: MutableLiveData<Int>
        get() = _pid

    fun postUser(){
        Log.e("TAG", "postUser: 유저 등록할게", )
        viewModelScope.launch {
            token.value?.let { pid.value?.let { it1 ->
                name.value?.let { it2 ->
                    userUseCase.postUserLogin(
                        it2, it,
                        it1
                    )
                }
            } }
        }
    }

    fun setName(name: String){
        viewModelScope.launch {
            _name.postValue(name)
        }
    }

    fun setToken(token: String){
        viewModelScope.launch{
            _token.postValue(token)
        }
    }

    fun setProfile(pid: Int){
        viewModelScope.launch {
            _pid.postValue(pid)
        }
    }
}