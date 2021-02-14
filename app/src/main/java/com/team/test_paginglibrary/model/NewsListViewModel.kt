package com.team.test_paginglibrary.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.team.test_paginglibrary.api.NetworkService
import com.team.test_paginglibrary.data.News
import com.team.test_paginglibrary.data.State
import com.team.test_paginglibrary.datasource.NewsDataSource
import com.team.test_paginglibrary.datasource.NewsDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel : ViewModel(){
    private val compositeDisposable = CompositeDisposable()
    private val networkService = NetworkService.getService()
    private val newsDataSourceFactory: NewsDataSourceFactory
    private val pageSize = 5
    var newsList: LiveData<PagedList<News>>

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config
            .Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, News>(newsDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource, State>(
        newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::state
    )

    fun retry(){
        newsDataSourceFactory.newsDataSourceLiveData?.value?.retry()
    }

    fun listIsEmpty(): Boolean{
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}