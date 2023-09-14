package com.barreto.newsapp.di

import android.app.Application
import androidx.room.Room
import com.barreto.newsapp.data.local.NewsDao
import com.barreto.newsapp.data.local.NewsDatabase
import com.barreto.newsapp.data.local.NewsTypeConverter
import com.barreto.newsapp.data.manger.LocalUserMangerImpl
import com.barreto.newsapp.data.remote.NewsApi
import com.barreto.newsapp.data.repository.NewRepositoryImpl
import com.barreto.newsapp.domain.manger.LocalUserManger
import com.barreto.newsapp.domain.repository.NewsRepository
import com.barreto.newsapp.domain.usecases.app_entry.AppEntryUsesCases
import com.barreto.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.barreto.newsapp.domain.usecases.app_entry.SaveAPPEntry
import com.barreto.newsapp.domain.usecases.news.GetNews
import com.barreto.newsapp.domain.usecases.news.NewsUseCases
import com.barreto.newsapp.domain.usecases.news.SearchNews
import com.barreto.newsapp.util.Constants.BASE_URL
import com.barreto.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerLocalUserManger(
        application: Application
    ) :LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUsesCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAPPEntry = SaveAPPEntry(localUserManger)
    )


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository = NewRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}