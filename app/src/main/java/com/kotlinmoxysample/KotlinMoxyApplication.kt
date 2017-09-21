package com.kotlinmoxysample

import android.app.Application
import com.kotlingithubapi.model.MyObjectBox
import io.objectbox.BoxStore
import timber.log.Timber

/**
 * Created by Valentyn on 9/18/17.
 */
class KotlinMoxyApplication : Application() {

    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance = this
        boxStore = MyObjectBox.builder().androidContext(instance).build()
    }

    companion object {
        lateinit var instance: KotlinMoxyApplication
            private set
    }
}