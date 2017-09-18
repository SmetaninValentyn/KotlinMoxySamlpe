package com.kotlinmoxysample.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatActivity

/**
 * Created by Valentyn on 9/18/17.
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    var mBackstack: FragmentBackStack? = null
    private var mRetainer: FragmentBackStack.Retainer? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initRetainBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRetainBackStack()
    }

    private fun initRetainBackStack() {
        mRetainer = supportFragmentManager.findFragmentByTag(FragmentBackStack.Retainer::class.java.simpleName) as FragmentBackStack.Retainer?

        // create the retain fragment and back stack the first time
        if (mRetainer == null) {
            initFragmentBackStack()
            // add the retain fragment
            mRetainer = FragmentBackStack.Retainer.newInstance()
            supportFragmentManager.beginTransaction().add(mRetainer, FragmentBackStack.Retainer::class.java.simpleName).commit()

            mRetainer?.retain(mBackstack)
        } else {
            mBackstack = mRetainer?.restore(supportFragmentManager)
            if (mBackstack == null) {
                initFragmentBackStack()
            }
        }
    }

    abstract fun setToolbarTitle(title: String)
    abstract fun setToolbarTitle(@StringRes resId: Int)
    abstract fun showProgress(show: Boolean)
    abstract fun initFragmentBackStack(): FragmentBackStack?


}