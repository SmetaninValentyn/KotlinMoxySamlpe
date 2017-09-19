package com.kotlinmoxysample.ui.contributors

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.kotlinmoxysample.ui.BaseActivity
import com.kotlinmoxysample.ui.FragmentBackStack
import kotlinx.android.synthetic.main.activity_contributor.*

@SuppressLint("Registered")

/**
 * Created by Valentyn on 9/18/17.
 */
// used activity for instead fragment for collapse toolbar
class ContributorActivity : BaseActivity(), ContributorView {

    @InjectPresenter
    lateinit var mPresenter : ContributorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)

        var contributor = intent.extras?.get(ARG_CONTRIBUTOR) as Contributor?

        mPresenter.loadContributor(contributor?.login)
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    override fun setToolbarTitle(resId: Int) {
        toolbar.title = resources.getString(resId)
    }

    override fun getToolbarTitle(): CharSequence? = toolbar.title

    override fun showProgress(show: Boolean) {
        // TODO progress
    }

    // activity without back stack
    override fun initFragmentBackStack(): FragmentBackStack? = null

    override fun showContributor(contributor: Contributor) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val ARG_CONTRIBUTOR = "arg_contributor"

        fun newIntent(activity: Activity, contributor: Contributor) : Intent {
            val intent = Intent(activity, ContributorActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ARG_CONTRIBUTOR, contributor)
            intent.putExtras(bundle)
            return intent
        }
    }

}