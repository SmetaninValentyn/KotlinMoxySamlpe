package com.kotlinmoxysample.controller.contributordetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kotlingithubapi.di.contributordetail.ContributorDetailModule
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.kotlinmoxysample.app.KotlinMoxyApplication
import com.kotlinmoxysample.controller.base.BaseActivity
import com.kotlinmoxysample.controller.FragmentBackStack
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_contributor_detail.*
import kotlinx.android.synthetic.main.contributor_content.*
import javax.inject.Inject


@SuppressLint("Registered")

/**
 * Created by Valentyn on 9/18/17.
 */
// used activity for instead fragment for collapse toolbar
class ContributorDetailActivity : BaseActivity(), ContributorDetailView {

    private val component by lazy { KotlinMoxyApplication.appComponent.plus(ContributorDetailModule()) }

    @Inject
    @InjectPresenter
    lateinit var mPresenter: ContributorDetailPresenter

    @ProvidePresenter
    fun providePresenter(): ContributorDetailPresenter = mPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val contributor = intent.extras?.get(ARG_CONTRIBUTOR) as Contributor?
        setImageTransition(intent.extras?.getString(ARG_TRANSITION_NAME))

        mPresenter.loadContributor(contributor)
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

        Picasso.with(this).load(path).placeholder(R.drawable.ic_github_placeholder).into(imageToolbar,
                object : Callback {
                    override fun onSuccess() {
                        supportStartPostponedEnterTransition()
                    }

                    override fun onError() {
                        supportStartPostponedEnterTransition()
                    }

                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setImageTransition(transitionName: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!transitionName.isNullOrEmpty()) {
                imageToolbar.transitionName = transitionName
            }
        }
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
        val ARG_TRANSITION_NAME = "arg_transitions_name"

        fun newIntent(activity: Activity, contributor: Contributor, transitionName : String? = null) : Intent {
            val intent = Intent(activity, ContributorDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ARG_CONTRIBUTOR, contributor)
            bundle.putString(ARG_TRANSITION_NAME, transitionName)
            intent.putExtras(bundle)
            return intent
        }
    }

}