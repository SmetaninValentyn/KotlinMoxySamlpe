package com.kotlinmoxysample.ui

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

/**
 * Created by Valentyn on 9/18/17.
 */
interface BaseFragmentView : MvpView {

    fun showProgress(show: Boolean)
    fun toast(@StringRes message: Int)
    fun toast(message: String)

}