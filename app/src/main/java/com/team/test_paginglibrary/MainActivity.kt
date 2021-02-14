package com.team.test_paginglibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.test_paginglibrary.data.State
import com.team.test_paginglibrary.datasource.NewsDataSourceFactory
import com.team.test_paginglibrary.model.NewsListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list_footer.*
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(NewsListViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        newsListAdapter = NewsListAdapter { viewModel.retry() }
        news_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        news_list.adapter = newsListAdapter
        viewModel.newsList.observe(this, {
            newsListAdapter.submitList(it)
        })
    }


    private fun initState() {
        error_notification.setOnClickListener { viewModel.retry() }
        viewModel.getState()
            .observe(this, { state ->
                Log.d("MainActivity", "viewModel.getState.observe")
                loading_progress_bar.visibility = if(state == State.LOADING) VISIBLE else INVISIBLE
                error_notification.visibility = if(viewModel.listIsEmpty() && state == State.ERROR) VISIBLE else View.GONE
                if(!viewModel.listIsEmpty()){
                    newsListAdapter.setState(state ?: State.DONE)
                }
            })
    }
}