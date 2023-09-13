package com.barreto.newsapp.di

import android.app.Application
import com.barreto.newsapp.data.manger.LocalUserMangerImpl
import com.barreto.newsapp.domain.manger.LocalUserManger
import com.barreto.newsapp.domain.usecases.AppEntryUsesCases
import com.barreto.newsapp.domain.usecases.ReadAppEntry
import com.barreto.newsapp.domain.usecases.SaveAPPEntry
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

}