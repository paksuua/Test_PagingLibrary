package com.team.test_paginglibrary.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.team.test_paginglibrary.data.Response
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService{
    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getNews(@Query("page") page: Int, @Query("pageSize")pageSize: Int): Single<Response>

    companion object{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getService(): NetworkService{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)

        }
    }

}