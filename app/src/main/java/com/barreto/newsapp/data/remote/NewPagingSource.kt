package com.barreto.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.barreto.newsapp.domain.model.Article
import com.barreto.newsapp.util.Constants.API_KEY

class NewPagingSource(
    private val newsApi: NewsApi,
    private val sources:String
) : PagingSource<Int,Article>(){

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newResponse = newsApi.getNews( page = page,sources = sources)
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

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }
}