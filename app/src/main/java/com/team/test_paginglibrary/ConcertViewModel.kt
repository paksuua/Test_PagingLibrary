package com.team.test_paginglibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class ConcertViewModel: ViewModel() {
    private val _concerts = MutableLiveData<PagedList<Concert>>()
    val concert: LiveData<PagedList<Concert>>
    get() = _concerts
}