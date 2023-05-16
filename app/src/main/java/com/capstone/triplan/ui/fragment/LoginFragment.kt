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
import com.capstone.triplan.presentation.viewModel.MainViewModel
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val mainModel : MainViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth


    override fun initView() {
        auth = FirebaseAuth.getInstance()
        signOut() //Todo 추후 로그아웃 버튼으로 기능 이동
        loginCheck()
        binding.apply {
            btnLogin.setOnClickListener {
                signInGoogle()
            }
        }

        mainModel.isLogin.observe(viewLifecycleOwner){
            if(it) findNavController().navigate(R.id.action_loginFragment_to_initialSettingNameFragment)
        }

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
                        loge("${account.givenName}")
                        mainModel.login()
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
}