package com.kotlinmoxysample.ui

import android.os.Bundle
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by Valentyn on 9/18/17.
 */
abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    protected lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity
    }

    override fun showProgress(show: Boolean) = mActivity.showProgress(show)
    override fun toast(@StringRes message: Int) = mActivity.toast(message)
    override fun toast(message: String) = mActivity.toast(message)

}