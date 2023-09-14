package com.barreto.newsapp.domain.repository

import androidx.paging.PagingData
import com.barreto.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources:List<String>): Flow<PagingData<Article>>
    fun searchNew(searchQuery: String,sources:List<String>): Flow<PagingData<Article>>


}