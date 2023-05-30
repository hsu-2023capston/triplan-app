package com.capstone.triplan.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.data.Prefs
import com.capstone.domain.model.DomainUser
import com.capstone.domain.model.Image
import com.capstone.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: Prefs,
    private val userUseCase: UserUseCase
): ViewModel() {
    private var _isLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLogin : MutableLiveData<Boolean>
        get() = _isLogin

    private var _isNew : MutableLiveData<Boolean> = MutableLiveData(false)
    val isNew : MutableLiveData<Boolean>
        get() = _isNew

    private val _user : MutableLiveData<DomainUser> = MutableLiveData()
    val user : MutableLiveData<DomainUser>
        get() = _user
    init {
        //TODO jwt바꾸는 부분
//        prefs.jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjo5LCJOQU1FIjoi6rmA7LCs7Z2sIiwiaWF0IjoxNjYzMDgwMTIwLCJleHAiOjE2ODkwMDAxMjAsImlzcyI6IkVDT1JFIn0.EDe4wa7TwsDB-y629HHI-SZf1RSLvw5Ip8fxmwGOyzA"
//        val jwt = JWT(prefs.jwt)
//        val user_id = jwt.getClaim("USER_ID").asInt()
//        if (user_id != null) {
//            prefs.user_id = user_id
//        }
//        Log.e("TAG", "해당 유저 아이디 $user_id", )
//
//        getUserInfo(prefs.user_id)
//        if (prefs.jwt!="NO_TOKEN"){
//            _isLogin.postValue(true)
//        }
    }

    fun signOut(){
        viewModelScope.launch {
            _isLogin.postValue(false)
        }
    }
    fun getUserLogin(access_token: String){
        viewModelScope.launch {
            val user = userUseCase.getUserLogin(access_token)
            _user.postValue(DomainUser(user.default_id, user_id = user.user_id, user_name = user.user_name, Message = user.Message))
            Log.e("TAG", "getUserLogin: hihihih", )
            if(user.Message == null){
                Log.e("TAG", "getUserLogin: 나얌", )
                _isLogin.postValue(true)
            }else{
                Log.e("TAG", "getUserLogin:제대로 걸렷서!", )
                _isNew.postValue(true)
            }
            Log.e("ㄸ", "getUserLogin: $user", )
        }
    }
}