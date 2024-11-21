package com.vikravch.mysipphone

import android.app.Application
import androidx.annotation.Keep
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@Keep
@HiltAndroidApp
class MySIPPhone: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}