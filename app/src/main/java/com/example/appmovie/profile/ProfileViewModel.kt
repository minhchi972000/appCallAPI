package com.example.appmovie.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovie.model.User

class ProfileViewModel: ViewModel() {
    private var _user : MutableLiveData<User> = MutableLiveData()
    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent : LiveData<Boolean>
        get()= _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent : LiveData<String>
        get()= _isErrorEvent
    fun checkEmail(email: String) {
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Ivalid email")
            return
        }
        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}