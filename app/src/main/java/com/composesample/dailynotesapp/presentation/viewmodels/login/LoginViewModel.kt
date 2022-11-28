package com.composesample.dailynotesapp.presentation.viewmodels.login;

import androidx.lifecycle.ViewModel
import com.composesample.dailynotesapp.domain.remote.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

}
