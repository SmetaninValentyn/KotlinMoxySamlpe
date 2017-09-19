package com.kotlinmoxysample.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatActivity
import com.kotlingithubapi.utils.toast

/**
 * Created by Valentyn on 9/18/17.
 */
abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    val ARG_TITLE : String = "arg_title"

    private var mTitle : String? = null

    var mBackstack: FragmentBackStack? = null
    private var mRetainer: FragmentBackStack.Retainer? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initRetainBackStack()
        mTitle = savedInstanceState?.getString(ARG_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRetainBackStack()
        mTitle = savedInstanceState?.getString(ARG_TITLE)

    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(mTitle ?: "")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(ARG_TITLE, getToolbarTitle().toString())
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(ARG_TITLE, getToolbarTitle().toString())
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

    override fun toast(message: Int) {
        this.applicationContext.toast(message)
    }

    override fun toast(message: String) {
        this.applicationContext.toast(message)
    }

    abstract fun setToolbarTitle(title: String)
    abstract fun setToolbarTitle(@StringRes resId: Int)
    abstract fun getToolbarTitle() : CharSequence?
    abstract fun initFragmentBackStack(): FragmentBackStack?


}