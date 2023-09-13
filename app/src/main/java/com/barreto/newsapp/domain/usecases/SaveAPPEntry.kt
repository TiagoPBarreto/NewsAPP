package com.barreto.newsapp.domain.usecases

import com.barreto.newsapp.domain.manger.LocalUserManger

class SaveAPPEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}