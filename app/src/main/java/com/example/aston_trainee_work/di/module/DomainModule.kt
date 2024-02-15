package com.example.aston_trainee_work.di.module

import com.example.aston_trainee_work.data.db.SourceRepository
import com.example.aston_trainee_work.data.db.SourceRepositoryImpl
import com.example.aston_trainee_work.data.network.ArticlesRepositoryImplJava
import com.example.aston_trainee_work.data.network.ArticlesRepositoryImplKOKLIN
import com.example.aston_trainee_work.domain.ArticlesRepositoryJava
import com.example.aston_trainee_work.domain.ArticlesRepositoryKOKLIN
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideArticlesRepositoryKoklin(impl: ArticlesRepositoryImplKOKLIN): ArticlesRepositoryKOKLIN {
        return impl
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(impl: ArticlesRepositoryImplJava): ArticlesRepositoryJava {
        return impl
    }

    @Provides
    @Singleton
    fun provideSourceRepository(impl: SourceRepositoryImpl): SourceRepository {
        return impl
    }
}