package com.kotlinmoxysample.controller.contributors

import dagger.Subcomponent

/**
 * Created by Valentyn on 9/26/17.
 */
@Subcomponent(modules = arrayOf(ContributorsModule::class))
@ForContributors
interface ContributorsComponent {
    fun inject(fragment: ContributorsFragment)
    fun inject(fragment: ContributorActivity)

    fun presenterContributors(): ContributorsPresenter
    fun presenterContributor(): ContributorPresenter

}