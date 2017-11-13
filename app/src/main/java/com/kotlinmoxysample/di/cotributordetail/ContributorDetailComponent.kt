package com.kotlingithubapi.di.contributordetail

import com.kotlinmoxysample.controller.contributordetail.ContributorDetailActivity
import com.kotlinmoxysample.controller.contributordetail.ContributorDetailPresenter
import dagger.Subcomponent

/**
 * Created by Valentyn on 9/26/17.
 */
@Subcomponent(modules = arrayOf(ContributorDetailModule::class))
@ForContributorDetail
interface ContributorDetailComponent {
    fun inject(fragment: ContributorDetailActivity)

    fun presenterContributorDetail(): ContributorDetailPresenter
}