package com.kotlinmoxysample.di

import android.content.Context
import com.kotlingithubapi.di.contributordetail.ContributorDetailComponent
import com.kotlingithubapi.di.contributordetail.ContributorDetailModule
import com.kotlingithubapi.di.contributors.ContributorsComponent
import com.kotlingithubapi.di.contributors.ContributorsModule
import com.kotlingithubapi.network.Api
import com.kotlinmoxysample.app.KotlinMoxyApplication
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
    fun plus(contributorDetailModule: ContributorDetailModule): ContributorDetailComponent
}