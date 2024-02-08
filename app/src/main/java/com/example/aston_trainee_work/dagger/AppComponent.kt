package com.example.aston_trainee_work.dagger

import com.example.aston_trainee_work.dagger.module.NavigationModule
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HeadlinesFragment)
}