package com.kotlinmoxysample.app

import android.app.Application
import com.kotlinmoxysample.di.AppComponent
import com.kotlinmoxysample.di.AppModule
import com.kotlinmoxysample.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Valentyn on 9/18/17.
 */
class KotlinMoxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

}