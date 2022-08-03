package com.example.appmovie.fragment.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignupViewModel() : ViewModel() {

    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent


    fun checkEmailAndPassword(email: String, password: String) {
//        val isValidName = isNameValid(name)
//        if (!isValidName) {
//            _isErrorEvent.postValue("Ivalid name")
//            return
//        }

        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Ivalid email")
            return
        }
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            return
        }

        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isNameValid(name: String): Boolean {
        if(name.length ==0){
            false
        }
        return true
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) {
            _isErrorEvent.postValue("Password with at least 8 characters")
            return false
        }
        val check = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()]).{8,}\$")
        if (!check.matcher(password).matches()) {
            _isErrorEvent.postValue("Password must contain uppercase, lowercase letters, numbers and special characters")
            return false
        }
        return true
    }


}