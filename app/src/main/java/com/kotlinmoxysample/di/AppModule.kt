package com.kotlinmoxysample.di

import android.content.Context
import com.kotlingithubapi.model.MyObjectBox
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import com.kotlinmoxysample.app.KotlinMoxyApplication
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

/**
 * Created by Valentyn on 9/25/17.
 */
@Module
class AppModule(val app: KotlinMoxyApplication) {

    @Provides
    @NotNull
    @Singleton
    fun providesContext(): Context = app

    @Provides
    @NotNull
    @Singleton
    fun providesApiClient(): Api = RestClient().createService()

    @Provides
    @NotNull
    @Singleton
    fun providesBoxStore(): BoxStore = MyObjectBox.builder().androidContext(app).build()

}