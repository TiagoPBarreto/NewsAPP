package com.barreto.newsapp.di

import android.app.Application
import androidx.room.Room
import com.barreto.newsapp.data.local.NewsDao
import com.barreto.newsapp.data.local.NewsDatabase
import com.barreto.newsapp.data.local.NewsTypeConverter
import com.barreto.newsapp.data.manger.LocalUserMangerImpl
import com.barreto.newsapp.domain.manger.LocalUserManger
import com.barreto.newsapp.domain.usecases.AppEntryUsesCases
import com.barreto.newsapp.domain.usecases.ReadAppEntry
import com.barreto.newsapp.domain.usecases.SaveAPPEntry
import com.barreto.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}