package com.team.test_paginglibrary.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team.test_paginglibrary.R
import com.team.test_paginglibrary.data.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder (view: View): RecyclerView.ViewHolder(view){
    fun bind(news: News?){
        news?.let{
            itemView.txt_news_name.text = news.title
            Glide.with(itemView).load(news.image).into(itemView.img_news_banner)
        }
    }

    companion object{
        fun create(parent: ViewGroup): NewsViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}