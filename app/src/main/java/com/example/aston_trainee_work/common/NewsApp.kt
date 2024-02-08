package com.example.aston_trainee_work.common

import android.app.Application
import com.example.aston_trainee_work.dagger.AppComponent
import com.example.aston_trainee_work.dagger.DaggerAppComponent

class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: NewsApp
    }
}