package com.kotlinmoxysample.app

import android.app.Application
import com.kotlingithubapi.model.MyObjectBox
import io.objectbox.BoxStore
import timber.log.Timber

/**
 * Created by Valentyn on 9/18/17.
 */
class KotlinMoxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance = this
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    companion object {
        lateinit var instance: KotlinMoxyApplication
            private set

        lateinit var appComponent: AppComponent
            private set
    }

}