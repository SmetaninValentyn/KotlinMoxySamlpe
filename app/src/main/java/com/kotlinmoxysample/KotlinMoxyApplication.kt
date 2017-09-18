package com.kotlinmoxysample

import android.app.Application
import timber.log.Timber

/**
 * Created by Valentyn on 9/18/17.
 */
class KotlinMoxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance = this
    }

    companion object {
        lateinit var instance: KotlinMoxyApplication
            private set
    }
}