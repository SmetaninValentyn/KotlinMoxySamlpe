package com.kotlinmoxysample.app

import android.app.Application
import com.kotlingithubapi.model.MyObjectBox
import com.kotlinmoxysample.controller.contributors.ContributorsComponent
import com.kotlinmoxysample.controller.contributors.ContributorsModule
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
        appComponent.inject(instance)

        contributorsComponent = plusChatComponent()
    }

    private fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    private fun plusChatComponent(): ContributorsComponent = appComponent.plusContributorsComponent(ContributorsModule())

    companion object {
        lateinit var instance: KotlinMoxyApplication
            private set

        lateinit var appComponent: AppComponent
            private set

        lateinit var contributorsComponent: ContributorsComponent
            private set
    }

}