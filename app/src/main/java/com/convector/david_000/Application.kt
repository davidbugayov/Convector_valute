package com.convector.david_000

import android.app.Application
import com.convector.david_000.convector_valute.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.text.Typography.dagger

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}