package com.team.test_paginglibrary.data

import com.squareup.moshi.Json
import com.team.test_paginglibrary.data.News

data class Response (
    @Json(name = "articles")
    val news: List<News>,
)