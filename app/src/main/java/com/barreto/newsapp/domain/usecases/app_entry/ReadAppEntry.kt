package com.barreto.newsapp.domain.usecases.app_entry

import com.barreto.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
     operator fun invoke() : Flow<Boolean>{
        return localUserManger.readAppEntry()
    }
}