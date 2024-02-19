package com.example.aston_trainee_work.di.module

import com.example.aston_trainee_work.data.db.SourceImageRepository
import com.example.aston_trainee_work.data.db.SourceImageRepositoryImpl
import com.example.aston_trainee_work.data.network.HeadlinesArticlesRepositoryImpl
import com.example.aston_trainee_work.data.network.SourceArticlesRepositoryImpl
import com.example.aston_trainee_work.data.network.SourcesRepositoryImpl
import com.example.aston_trainee_work.domain.HeadlinesArticlesRepository
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
}