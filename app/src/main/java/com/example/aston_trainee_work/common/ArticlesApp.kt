package com.example.aston_trainee_work.common

import android.app.Application
import com.example.aston_trainee_work.di.AppComponent
import com.example.aston_trainee_work.di.DaggerAppComponent

class ArticlesApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: ArticlesApp
    }
}