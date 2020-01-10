package com.talview.yasma.samsruti

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class YasmaApplication : Application() {

//    TODO: Make periodic fetch requests for all posts and albums

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}