package com.example.aston_trainee_work.di.module

import com.example.aston_trainee_work.domain.SavedArticleRepository
import com.example.aston_trainee_work.data.db.SavedArticleRepositoryImpl
import com.example.aston_trainee_work.domain.SourceImageRepository
import com.example.aston_trainee_work.data.db.SourceImageRepositoryImpl
import com.example.aston_trainee_work.data.network.HeadlinesArticlesRepositoryImpl
import com.example.aston_trainee_work.data.network.SearchArticlesRepositoryImpl
import com.example.aston_trainee_work.data.network.SourceArticlesRepositoryImpl
import com.example.aston_trainee_work.data.network.SourcesRepositoryImpl
import com.example.aston_trainee_work.domain.HeadlinesArticlesRepository
import com.example.aston_trainee_work.domain.SearchArticlesRepository
import com.example.aston_trainee_work.domain.SourceArticlesRepository
import com.example.aston_trainee_work.domain.SourcesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideSourcesRepository(impl: SourcesRepositoryImpl): SourcesRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(impl: HeadlinesArticlesRepositoryImpl): HeadlinesArticlesRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideSourceArticlesRepository(impl: SourceArticlesRepositoryImpl): SourceArticlesRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideSourceImageRepository(impl: SourceImageRepositoryImpl): SourceImageRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideSavedArticleRepository(impl: SavedArticleRepositoryImpl): SavedArticleRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideSearchArticlesRepository(impl: SearchArticlesRepositoryImpl): SearchArticlesRepository {
        return impl
    }
}