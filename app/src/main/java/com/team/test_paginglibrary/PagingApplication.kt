package com.team.test_paginglibrary

import android.app.Application
import timber.log.Timber

class PagingApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}