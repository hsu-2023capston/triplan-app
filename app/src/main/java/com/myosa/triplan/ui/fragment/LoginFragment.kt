package com.myosa.triplan.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.activityViewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentLoginBinding
import com.myosa.triplan.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val mainModel : MainViewModel by activityViewModels()


    override fun initView() {
        loginCheck()
        binding.apply {
            btnLogin.setOnClickListener {
                signInGoogle()
            }
        }

    }

    private fun loginCheck(){
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {//로그인 안함
            Log.e("TAG", "로그인 o: ", )
        } else {//로그인 한 상태
            Log.e("TAG", "로그인 x: ", )
        }
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
                Log.e("TAG", "handleResults: dddd${account.email}", )
//                mainModel.login()
            }
        }else{
            Log.e("res err", "handleResults: ${task.exception.toString()}", )
        }
    }
}