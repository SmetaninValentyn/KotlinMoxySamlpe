package com.kotlinmoxysample.controller.contributors

import android.view.View
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.controller.base.BaseView

/**
 * Created by Valentyn on 9/18/17.
 */
interface ContributorsView : BaseView {

    fun onContributorClick(contributor: Contributor?, avatarView: View? = null)
    fun showContributors(contributors: List<Contributor>?)

}