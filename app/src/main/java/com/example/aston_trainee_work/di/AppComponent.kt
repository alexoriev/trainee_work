package com.example.aston_trainee_work.di

import com.example.aston_trainee_work.di.module.DataModule
import com.example.aston_trainee_work.di.module.NavigationModule
import com.example.aston_trainee_work.di.module.DomainModule
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun getGetHeadlinesArticlesListUseCase(): GetHeadlinesArticlesListUseCase
    fun inject(activity: MainActivity)
    fun inject(fragment: HeadlinesFragment)
}