package com.barreto.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.barreto.newsapp.domain.model.Article

class SearchNewsPageResource(
    private val newsApi: NewsApi,
    private val searchQuery:String,
    private val sources:String
) :PagingSource<Int,Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newResponse = newsApi.searchNews( searchQuery = searchQuery,page = page,sources = sources)
            totalNewsCount += newResponse.articles.size
            val articles = newResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


}