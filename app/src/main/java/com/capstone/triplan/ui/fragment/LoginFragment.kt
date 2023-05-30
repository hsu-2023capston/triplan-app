package com.capstone.triplan.ui.fragment

import android.app.Activity
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentLoginBinding
import com.capstone.triplan.presentation.presentation.User
import com.capstone.triplan.presentation.viewModel.InitialSettingViewModel
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val mainModel : MainViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private val isModel : InitialSettingViewModel by activityViewModels()

    private lateinit var dbRef : DatabaseReference


    override fun initView() {
        auth = FirebaseAuth.getInstance()
        //signOut() //Todo 추후 로그아웃 버튼으로 기능 이동
        //loginCheck()
        binding.apply {
            btnLogin.setOnClickListener {
                signInGoogle()
            }
            button2.setOnClickListener {
                signOut()
            }
        }

        setObserver()
    }

    private fun loginCheck(){
        if (auth.currentUser == null) {
            loge("로그인 X")
        } else {//로그인 한 상태
            loge("로그인 O")
        }
    }

    private fun signOut() {
        auth.signOut()
        GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
    }

    private fun signInGoogle(){
        val googleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity() , googleSignInOptions)

        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    private val launcher = registerForActivityResult(StartActivityForResult()){
            result ->
        Log.e("TAG", ": $result", )
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }


    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                auth.signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){ //성공시
                        loge("${account.givenName}, ${account.idToken} ${auth.currentUser!!.uid}")
                            //addUserToFRDatabase(1, it1," Test")
                            //mainModel.getUserLogin("token")
                        mainModel.getUserLogin(auth.currentUser!!.uid)
                        isModel.setToken(auth.currentUser!!.uid)
                        account.givenName?.let { it1 -> isModel.setName(it1) }
                    }
                    else{
                        loge("err")
                    }
                }
            }
        }else{
            Log.e("res err", "handleResults: ${task.exception.toString()}", )
        }
    }

    private fun addUserToFRDatabase(uid: Int, uToken: String, name: String){
        dbRef = FirebaseDatabase.getInstance("https://triplan-38f12-default-rtdb.firebaseio.com/").getReference()

//        val map = HashMap<String,String>();
//        map["uid"] = uid.toString()
//        map["name"] = name
//        dbRef.child("user").child(uid.toString()).setValue(map)
//        loge("hi")
    }

    private fun setObserver(){
        mainModel.isNew.observe(viewLifecycleOwner){
             if (it){
                 loge("나 새삥인디")
                 findNavController().navigate(R.id.action_loginFragment_to_initialSettingNameFragment)
             }
        }
        mainModel.isLogin.observe(viewLifecycleOwner){
            if(it){ //findNavController().navigate(R.id.action_loginFragment_to_initialSettingNameFragment)
                loge("나 Db에 있는놈")
                findNavController().navigate(R.id.action_loginFragment_to_mainHomeFragment)
            }
        }
    }
}