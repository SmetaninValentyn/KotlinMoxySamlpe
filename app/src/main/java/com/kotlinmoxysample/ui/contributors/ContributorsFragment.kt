package com.kotlinmoxysample.ui.contributors

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.kotlinmoxysample.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_contributors.*

/**
 * Created by Valentyn on 9/18/17.
 */
class ContributorsFragment : BaseFragment(), ContributorsView {

    @InjectPresenter
    lateinit var mPresenter: ContributorsPresenter

    private var mAdapter: ContributorsAdapter? = null

    override fun onResume() {
        super.onResume()
        mActivity.setToolbarTitle(R.string.contributors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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

        mAdapter = ContributorsAdapter(ArrayList()) {
            mPresenter.onContributorClick(it)
        }

        rvContributors.adapter = mAdapter
    }

    override fun showContributors(contributors: List<Contributor>?) {
        mAdapter?.items = contributors
        mAdapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): ContributorsFragment = ContributorsFragment()
    }
}