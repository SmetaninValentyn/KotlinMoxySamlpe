package com.kotlinmoxysample.controller.contributors

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.kotlinmoxysample.app.KotlinMoxyApplication
import com.kotlinmoxysample.controller.BaseFragment
import com.kotlinmoxysample.controller.contributors.ContributorsAdapter.ContributorClickListener
import kotlinx.android.synthetic.main.fragment_contributors.*
import javax.inject.Inject
import com.arellomobile.mvp.presenter.ProvidePresenter

/**
 * Created by Valentyn on 9/18/17.
 */
class ContributorsFragment : BaseFragment(), ContributorsView {

    @Inject
    @InjectPresenter
    lateinit var mPresenter: ContributorsPresenter

    private var mAdapter: ContributorsAdapter? = null

    override fun onResume() {
        super.onResume()
        mActivity.setToolbarTitle(R.string.contributors)
    }

    @ProvidePresenter
    fun providePresenter(): ContributorsPresenter = mPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        KotlinMoxyApplication.appComponent.plusContributorsComponent(ContributorsModule()).inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_contributors, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        mPresenter.loadContributors()
    }

    private fun initRecycler() {
        rvContributors.setHasFixedSize(true)

        rvContributors.layoutManager = LinearLayoutManager(mActivity)

        val dividerItemDecoration = DividerItemDecoration(rvContributors.context,
                LinearLayoutManager.VERTICAL)
        rvContributors.addItemDecoration(dividerItemDecoration)

        mAdapter = ContributorsAdapter(
                mActivity,
                ArrayList(),
                object : ContributorClickListener {
                    override fun onContributorClick(contributor: Contributor?, viewAvatar: View?) {
                        mPresenter.onContributorClicked(contributor, viewAvatar)
                    }
                }
        )

        rvContributors.adapter = mAdapter
    }

    override fun onContributorClick(contributor: Contributor?, avatarView: View?) {
        if(contributor != null) {
            val intent = ContributorActivity.newIntent(mActivity, contributor, ViewCompat.getTransitionName(avatarView))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    avatarView, ViewCompat.getTransitionName(avatarView))
            startActivity(intent, options.toBundle())
        }
    }

    override fun showContributors(contributors: List<Contributor>?) {
        mAdapter?.items = contributors
        mAdapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): ContributorsFragment = ContributorsFragment()
    }
}