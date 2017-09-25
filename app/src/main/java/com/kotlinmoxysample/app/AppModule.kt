package com.kotlinmoxysample.app

import android.content.Context
import com.kotlingithubapi.model.MyObjectBox
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import com.kotlinmoxysample.db.BaseDao
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
    fun provideContext(): KotlinMoxyApplication = app

    @Provides
    @NotNull
    @Singleton
    fun provideApiClient(): Api = RestClient().createService(Api::class.java)

    @Provides
    @NotNull
    @Singleton
    fun provideBoxStore(context: Context): BoxStore = MyObjectBox.builder().androidContext(context).build()

    @Provides
    @NotNull
    @Singleton
    fun provideBaseDao(boxStore: BoxStore): BaseDao = BaseDao(boxStore)



}