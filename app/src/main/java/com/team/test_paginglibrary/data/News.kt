package com.team.test_paginglibrary.data

import com.squareup.moshi.Json

data class News(
    val title: String,
    @Json(name = "urlToImage")
    val image: String
)