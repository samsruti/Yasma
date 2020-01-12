package com.talview.yasma.samsruti

import android.app.Application
import com.talview.yasma.samsruti.di.networkModule
import com.talview.yasma.samsruti.di.repositoryModule
import com.talview.yasma.samsruti.di.roomModule
import com.talview.yasma.samsruti.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class YasmaApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@YasmaApplication)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule))
        }

    }


}
