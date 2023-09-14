package com.barreto.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.barreto.newsapp.data.remote.NewPagingSource
import com.barreto.newsapp.data.remote.NewsApi
import com.barreto.newsapp.data.remote.SearchNewsPageResource
import com.barreto.newsapp.domain.model.Article
import com.barreto.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewRepositoryImpl(
    private val newsApi: NewsApi
):NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return  Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNew(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return  Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPageResource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}