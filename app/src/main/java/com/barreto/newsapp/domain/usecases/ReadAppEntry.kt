package com.barreto.newsapp.domain.usecases

import com.barreto.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke() : Flow<Boolean>{
        return localUserManger.readAppEntry()
    }
}