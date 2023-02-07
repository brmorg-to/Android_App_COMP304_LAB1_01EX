package com.hfad.brunomorgado_comp304section002_lab01_ex01

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "AppViewModel"

const val USERNAME = "username"
const val PASSWORD = "password"

class AppViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val _username = MutableLiveData<String>().apply {
        value = savedStateHandle.get<String>(USERNAME) ?: "User Name"
    }
    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>().apply {
        value = savedStateHandle.get<String>(PASSWORD) ?: "Password"
    }
    val password: LiveData<String>
        get() = _password

    fun saveCredentials(username: String, password: String) {
        savedStateHandle[USERNAME] = username
        savedStateHandle[PASSWORD] = password
        _username.value = username
        _password.value = password
    }

}