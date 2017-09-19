package com.kotlinmoxysample.ui.contributors

import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.ui.BaseView

/**
 * Created by Valentyn on 9/18/17.
 */
interface ContributorView : BaseView {

    fun showContributor(contributor: Contributor?)

}