package com.kotlinmoxysample.controller.contributordetail

import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.controller.base.BaseView

/**
 * Created by Valentyn on 9/18/17.
 */
interface ContributorDetailView : BaseView {

    fun showContributor(contributor: Contributor?)

}