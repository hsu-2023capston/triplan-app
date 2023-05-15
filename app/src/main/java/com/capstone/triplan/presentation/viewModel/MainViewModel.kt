package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.data.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: Prefs
): ViewModel() {
    private var _isLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLogin : MutableLiveData<Boolean>
        get() = _isLogin

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
        if (prefs.jwt!="NO_TOKEN"){
            _isLogin.postValue(true)
        }
    }

    fun login(){
        _isLogin.postValue(true)
    }

}