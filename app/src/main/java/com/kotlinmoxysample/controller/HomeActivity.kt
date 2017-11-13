package com.kotlinmoxysample.controller

import android.os.Bundle
import android.view.View
import com.kotlinmoxysample.R
import com.kotlinmoxysample.controller.base.BaseActivity
import com.kotlinmoxysample.controller.contributors.ContributorsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Created by Valentyn on 9/18/17.
 */
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun setToolbarTitle(title: String) {
        toolbar.toolbar.title = title
    }

    override fun setToolbarTitle(resId: Int) {
        toolbar.toolbar.title = resources.getString(resId)
    }

    override fun getToolbarTitle(): CharSequence? = toolbar?.toolbar?.title

    override fun showProgress(show: Boolean) =
            if(show) progress.visibility = View.VISIBLE
            else progress.visibility =  View.GONE

    override fun initFragmentBackStack() : FragmentBackStack? {
        if(mBackstack == null) {
            mBackstack = FragmentBackStack(supportFragmentManager, R.id.fragmentContainer)
            mBackstack?.replace(ContributorsFragment.newInstance())
        }
        return mBackstack
    }

}