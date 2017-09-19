package com.kotlinmoxysample.ui.contributors

import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.ui.BaseView

/**
 * Created by Valentyn on 9/18/17.
 */
interface ContributorsView : BaseView {

    fun onContributorClick(contributor: Contributor?)
    fun showContributors(contributors: List<Contributor>?)

}