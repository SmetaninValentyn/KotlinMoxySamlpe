package com.kotlinmoxysample.app

import com.kotlinmoxysample.ui.BaseActivity
import dagger.Component

/**
 * Created by Valentyn on 9/25/17.
 */
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(activity: BaseActivity)
}