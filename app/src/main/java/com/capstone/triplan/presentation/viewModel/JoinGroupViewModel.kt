package com.capstone.triplan.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.capstone.domain.usecase.GroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinGroupViewModel @Inject constructor(
    private val groupUseCase: GroupUseCase
): ViewModel(){
    
}