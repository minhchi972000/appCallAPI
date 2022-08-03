package com.example.appmovie.app

import android.app.Application
import com.example.appmovie.MySharedPreferences


class  MyApp : Application() {
    lateinit var prefs : MySharedPreferences
    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences()
        prefs.init(this)
    }
}