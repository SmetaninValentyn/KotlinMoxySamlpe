package com.kotlinmoxysample.app

import android.content.Context
import com.kotlingithubapi.network.Api
import com.kotlinmoxysample.controller.contributors.ContributorsComponent
import com.kotlinmoxysample.controller.contributors.ContributorsModule
import dagger.Component
import io.objectbox.BoxStore
import javax.inject.Singleton

/**
 * Created by Valentyn on 9/25/17.
 */
@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun context(): Context
    fun boxStore(): BoxStore
    fun api(): Api

    fun inject(application: KotlinMoxyApplication)

    fun plus(contributorsModule: ContributorsModule): ContributorsComponent
}