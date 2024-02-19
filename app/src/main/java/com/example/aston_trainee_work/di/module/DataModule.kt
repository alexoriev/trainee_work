package com.example.aston_trainee_work.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.data.api.HeadlinesArticlesApiService
import com.example.aston_trainee_work.data.api.SourceArticlesApiService
import com.example.aston_trainee_work.data.api.SourcesApiService
import com.example.aston_trainee_work.data.db.AppDb
import com.example.aston_trainee_work.data.db.SourceDao
import com.example.aston_trainee_work.data.db.SourceImageEntity
import com.example.aston_trainee_work.data.db.SourceImageRepository
import com.example.aston_trainee_work.utils.SourceConverter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Provider
import javax.inject.Singleton

@Module
class DataModule(val app: Application) {

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
    ): HeadlinesArticlesApiService = retrofit.create()

    @Provides
    @Singleton
    fun provideSourcesApiService(
        retrofit: Retrofit,
    ): SourcesApiService = retrofit.create()

    @Provides
    @Singleton
    fun provideSourceArticlesApiService(
        retrofit: Retrofit,
    ): SourceArticlesApiService = retrofit.create()

    @Provides
    @Singleton
    fun provideSourceConverter(
        sourceImageRepository: SourceImageRepository
    ): SourceConverter {
        return SourceConverter(sourceImageRepository)
    }

    @Singleton
    @Provides
    fun provideDb(sourceProvider: Provider<SourceDao>): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "app.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                private val applicationScope = CoroutineScope(SupervisorJob())

                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    applicationScope.launch(Dispatchers.IO) {
                        val sourceDao = sourceProvider.get()
                        sourceDao.insert(listOf(
                            SourceImageEntity("abc-news", R.drawable.abc),
                            SourceImageEntity("abc-news-au", R.drawable.abc),
                            SourceImageEntity("aftenposten", R.drawable.aftenposten),
                            SourceImageEntity("al-jazeera-english", R.drawable.aljazeera),
                            SourceImageEntity("ansa", R.drawable.ansa),
                            SourceImageEntity("ars-technica", R.drawable.ars),
                            SourceImageEntity("associated-press", R.drawable.ap),
                            SourceImageEntity("bbc-news", R.drawable.bbc),
                            SourceImageEntity("bbc-sport", R.drawable.bbc),
                            SourceImageEntity("bild", R.drawable.bild),
                            SourceImageEntity("bloomberg", R.drawable.bloomberg),
                            SourceImageEntity("cnn", R.drawable.cnn),
                            SourceImageEntity("cnn-es", R.drawable.cnn),
                            SourceImageEntity("engadget", R.drawable.engadget),
                            SourceImageEntity("financial-post", R.drawable.fp),
                            SourceImageEntity("fox-news", R.drawable.fox),
                            SourceImageEntity("hacker-news", R.drawable.hacker_news),
                            SourceImageEntity("il-sole-24-ore", R.drawable.il24),
                            SourceImageEntity("google-news", R.drawable.google_news),
                            SourceImageEntity("google-news-ar", R.drawable.google_news),
                            SourceImageEntity("google-news-au", R.drawable.google_news),
                            SourceImageEntity("google-news-br", R.drawable.google_news),
                            SourceImageEntity("google-news-ca", R.drawable.google_news),
                            SourceImageEntity("google-news-fr", R.drawable.google_news),
                            SourceImageEntity("google-news-in", R.drawable.google_news),
                            SourceImageEntity("google-news-is", R.drawable.google_news),
                            SourceImageEntity("google-news-it", R.drawable.google_news),
                            SourceImageEntity("google-news-ru", R.drawable.google_news),
                            SourceImageEntity("google-news-sa", R.drawable.google_news),
                            SourceImageEntity("google-news-uk", R.drawable.google_news),
                            SourceImageEntity("t3n", R.drawable.t3n),
                            SourceImageEntity("techcrunch", R.drawable.techcrunch),
                            SourceImageEntity("techcrunch-cn", R.drawable.techcrunch),
                            SourceImageEntity("techradar", R.drawable.techradar),
                            SourceImageEntity("the-next-web", R.drawable.tnw),
                            SourceImageEntity("the-verge", R.drawable.theverge),
                            SourceImageEntity("the-wall-street-journal", R.drawable.twj),
                            SourceImageEntity("the-washington-times", R.drawable.times),
                            SourceImageEntity("wired", R.drawable.wired),
                            SourceImageEntity("wired-de", R.drawable.wired)
                        ))
                    }
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideSourceDao(
        appDb: AppDb
    ): SourceDao = appDb.sourceDao()
}