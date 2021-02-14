package com.team.test_paginglibrary.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.team.test_paginglibrary.api.NetworkService
import com.team.test_paginglibrary.data.News
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
): DataSource.Factory<Int, News>(){
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()
    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        Log.d("DataSourceFactory", "newsDataSourceLiveData.postValue(newsDataSource)")
        return newsDataSource
    }
}