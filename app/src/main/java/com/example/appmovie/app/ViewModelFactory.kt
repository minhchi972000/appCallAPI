package com.example.appmovie.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appmovie.fragment.login.LoginViewModel


class ViewModelFactory(val app : MyApp) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(app.prefs) as T
        }
        throw IllegalArgumentException("unknown view model")
    }
}