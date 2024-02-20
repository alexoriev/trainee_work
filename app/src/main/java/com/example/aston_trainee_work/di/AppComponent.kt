package com.example.aston_trainee_work.di

import com.example.aston_trainee_work.di.module.DataModule
import com.example.aston_trainee_work.di.module.NavigationModule
import com.example.aston_trainee_work.di.module.DomainModule
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase
import com.example.aston_trainee_work.domain.GetSavedArticlesListUseCase
import com.example.aston_trainee_work.domain.GetSourceArticlesListUseCase
import com.example.aston_trainee_work.domain.GetSourcesListUseCase
import com.example.aston_trainee_work.domain.IsSavedArticleUseCase
import com.example.aston_trainee_work.domain.DeleteOldSavedArticlesUseCase
import com.example.aston_trainee_work.domain.SaveArticleUseCase
import com.example.aston_trainee_work.presentation.ArticleProfileViewModel
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.MainActivity
import com.example.aston_trainee_work.utils.SourceConverter
import com.github.terrakok.cicerone.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun getGetHeadlinesArticlesListUseCase(): GetHeadlinesArticlesListUseCase
    fun getGetSourcesListUseCase(): GetSourcesListUseCase
    fun getGetSourceArticlesListUseCase(): GetSourceArticlesListUseCase
    fun getSaveArticleUseCase(): SaveArticleUseCase
    fun getIsSavedArticleUseCase(): IsSavedArticleUseCase
    fun getSourceConverter(): SourceConverter
    fun getGetSavedArticlesListUseCase(): GetSavedArticlesListUseCase
    fun getDeleteOldSavedArticlesUseCase(): DeleteOldSavedArticlesUseCase
    fun getRouter(): Router
    fun inject(activity: MainActivity)
    fun inject(fragment: HeadlinesFragment)
    fun inject(articleProfileViewModel: ArticleProfileViewModel)
}