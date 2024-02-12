package com.example.aston_trainee_work.di.module

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.api.ArticlesApiServiceJava
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideArticlesApiService(
        retrofit: Retrofit,
    ): ArticlesApiServiceJava = retrofit.create()

}