package com.talview.yasma.samsruti

import android.app.Application
import com.talview.yasma.samsruti.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class YasmaApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val appComponent = AppComponent()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@YasmaApplication)

            modules(appComponent.getComponentModuleList())
        }

    }


}
