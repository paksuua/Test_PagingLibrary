package com.team.test_paginglibrary.util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueue(
    

) {
    this.enqueue(
        object: Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
    )
}