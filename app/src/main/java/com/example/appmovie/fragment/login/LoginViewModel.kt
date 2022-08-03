package com.example.appmovie.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovie.MySharedPreferences
import com.example.appmovie.model.User
import java.util.regex.Pattern

class LoginViewModel (val sharedPrefs: MySharedPreferences): ViewModel() {


    private var _saveEventSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val saveEventSuccess: LiveData<Boolean>
        get() = _saveEventSuccess

    private var _loadUserEvent: MutableLiveData<User> = MutableLiveData()
    val loadUserEvent: LiveData<User>
        get() = _loadUserEvent

    fun saveUserInfo(email: String, password: String,check:Boolean) {
        sharedPrefs.saveeEmail(email)
        sharedPrefs.savePassword(password)
        sharedPrefs.saveCheck(check)
        _saveEventSuccess.value = true
    }

//    fun loadUserInfo() {
//        val username = sharedPrefs.getUsername()
//        val password = sharedPrefs.getPassword()
//        _loadUserEvent.postValue(User(username ?: "", password ?: ""))
//    }

    //==================
    private var _isSuccessEvent:MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent :LiveData<Boolean>
        get()= _isSuccessEvent

    private var _isErrorEvent:MutableLiveData<String> = MutableLiveData()
    val isErrorEvent :LiveData<String>
        get()= _isErrorEvent

    fun checkEmailAndPassword (email:String,password:String){
        val isvalidemail= isValidEmail(email)
        val isvalidpassword= isPasswordValid(password)
        if(!isvalidemail){
            _isErrorEvent.postValue("Ivalid email")
            return
        }
        if(!isvalidpassword){
            _isErrorEvent.postValue("Ivalid password")
            return
        }
        return _isSuccessEvent.postValue(true)
    }

    private fun isValidEmail(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.length < 8){
            _isErrorEvent.postValue("Password with at least 8 characters")
            return false
        }
        val check = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()]).{8,}\$")
        if (!check.matcher(password).matches()){
            _isErrorEvent.postValue("Password must contain uppercase, lowercase letters, numbers and special characters")
            return false
        }
        return true
    }
}