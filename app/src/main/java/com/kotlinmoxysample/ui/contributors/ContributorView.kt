package com.kotlinmoxysample.ui.contributors

import com.arellomobile.mvp.MvpView
import com.kotlingithubapi.model.Contributor

/**
 * Created by Valentyn on 9/18/17.
 */
interface ContributorView : MvpView {

    fun showContributor(contributor: Contributor)

}