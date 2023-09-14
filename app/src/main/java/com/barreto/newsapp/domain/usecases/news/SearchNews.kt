package com.barreto.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.barreto.newsapp.domain.model.Article
import com.barreto.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery:String,sources:List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNew(searchQuery = searchQuery,sources = sources)
    }

}