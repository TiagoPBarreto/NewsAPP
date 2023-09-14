package com.barreto.newsapp.data.remote.dto

import com.barreto.newsapp.domain.model.Article

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)