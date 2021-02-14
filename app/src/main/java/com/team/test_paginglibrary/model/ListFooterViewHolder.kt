package com.team.test_paginglibrary.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.test_paginglibrary.R
import com.team.test_paginglibrary.data.State
import kotlinx.android.synthetic.main.item_list_footer.view.*

class ListFooterViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bind(status: State?){
        itemView.progress_bar.visibility = if(status == State.LOADING) View.VISIBLE else View.INVISIBLE
        itemView.txt_error.visibility = if(status == State.ERROR) View.INVISIBLE else View.VISIBLE
    }

    companion object{
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener{retry()}
            return ListFooterViewHolder(view)
        }
    }
}
