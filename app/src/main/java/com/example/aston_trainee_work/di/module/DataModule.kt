package com.example.aston_trainee_work.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.data.api.ArticlesApiServiceJava
import com.example.aston_trainee_work.data.db.AppDb
import com.example.aston_trainee_work.data.db.SourceDao
import com.example.aston_trainee_work.data.db.SourceEntity
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
    ): ArticlesApiServiceJava = retrofit.create()

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
                            SourceEntity("abc-news", "ABC News", R.drawable.abc),
                            SourceEntity("abc-news-au", "ABC News (AU)", R.drawable.abc),
                            SourceEntity("aftenposten", "Aftenposten", R.drawable.aftenposten),
                            SourceEntity("al-jazeera-english", "Al Jazeera English", R.drawable.aljazeera),
                            SourceEntity("ansa", "ANSA.IT", R.drawable.ansa),
                            SourceEntity("ars-technica", "Ars Technica", R.drawable.ars),
                            SourceEntity("associated-press", "Associated Press", R.drawable.ap),
                            SourceEntity("bbc-news", "BBC News", R.drawable.bbc),
                            SourceEntity("bild", "Bild", R.drawable.bild),
                            SourceEntity("bloomberg", "Bloomberg", R.drawable.bloomberg),
                            SourceEntity("cnn", "CNN", R.drawable.cnn),
                            SourceEntity("cnn-es", "CNN Spanish", R.drawable.cnn),
                            SourceEntity("engadget", "Engadget", R.drawable.engadget),
                            SourceEntity("financial-post", "CNN", R.drawable.fp),
                            SourceEntity("fox-news", "Financial Post", R.drawable.fox),
                            SourceEntity("hacker-news", "Hacker News", R.drawable.hacker_news),
                            SourceEntity("il-sole-24-ore", "Il Sole 24 Ore", R.drawable.il24),
                            SourceEntity("google-news", "Google News", R.drawable.google_news),
                            SourceEntity("t3n", "T3n", R.drawable.t3n),
                            SourceEntity("techcrunch", "TechCrunch", R.drawable.techcrunch),
                            SourceEntity("techcrunch-cn", "TechCrunch (CN)", R.drawable.techcrunch),
                            SourceEntity("techradar", "TechRadar", R.drawable.techradar),
                            SourceEntity("the-next-web", "The Next Web", R.drawable.tnw),
                            SourceEntity("the-verge", "The Verge", R.drawable.theverge),
                            SourceEntity("the-wall-street-journal", "The Wall Street Journal", R.drawable.twj),
                            SourceEntity("the-washington-times","The Washington Times", R.drawable.times),
                            SourceEntity("wired", "Wired", R.drawable.wired),
                            SourceEntity("wired-de", "Wired.de", R.drawable.wired)
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