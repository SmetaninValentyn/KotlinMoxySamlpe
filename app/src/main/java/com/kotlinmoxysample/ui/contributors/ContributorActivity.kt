package com.kotlinmoxysample.ui.contributors

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.kotlinmoxysample.ui.BaseActivity
import com.kotlinmoxysample.ui.FragmentBackStack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_contributor.*
import kotlinx.android.synthetic.main.contributor_content.*

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

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var contributor = intent.extras?.get(ARG_CONTRIBUTOR) as Contributor?
        showContributor(contributor)

        mPresenter.loadContributor(contributor?.login)
    }

    override fun showContributor(contributor: Contributor?) {
        if(contributor?.login != null) {
            setToolbarTitle(contributor.login)
        }
        loadAvatar(contributor?.avatarUrl)

        if(contributor?.name.isNullOrEmpty()) {
            linearName.visibility = View.GONE
        } else {
            tvName.text = contributor?.name
            linearName.visibility = View.VISIBLE
        }

        if(contributor?.email.isNullOrEmpty()) {
            linearEmail.visibility = View.GONE
        } else {
            tvEmail.text = contributor?.email
            linearEmail.visibility = View.VISIBLE
        }

        if(contributor?.company.isNullOrEmpty()) {
            linearCompany.visibility = View.GONE
        } else {
            tvCompany.text = contributor?.company
            linearCompany.visibility = View.VISIBLE
        }

        if(contributor?.location.isNullOrEmpty()) {
            linearLocation.visibility = View.GONE
        } else {
            tvLocation.text = contributor?.location
            linearLocation.visibility = View.VISIBLE
        }
    }

    private fun loadAvatar(path: String?) {
        if(path.isNullOrEmpty()) return

        Picasso.with(this).load(path).into(imageToolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    override fun setToolbarTitle(resId: Int) {
        toolbar.title = resources.getString(resId)
    }

    override fun getToolbarTitle(): CharSequence? = toolbar.title

    override fun showProgress(show: Boolean) {}

    // activity without back stack
    override fun initFragmentBackStack(): FragmentBackStack? = null

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