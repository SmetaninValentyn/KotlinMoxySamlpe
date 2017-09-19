package com.kotlinmoxysample.ui

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

/**
 * Created by Valentyn on 9/19/17.
 */
interface BaseView : MvpView {

    fun toast(@StringRes message: Int)
    fun toast(message: String)
    fun showProgress(show: Boolean)

}