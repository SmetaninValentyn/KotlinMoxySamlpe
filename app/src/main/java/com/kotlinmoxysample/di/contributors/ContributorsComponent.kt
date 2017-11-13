package com.kotlingithubapi.di.contributors

import com.kotlinmoxysample.controller.contributors.ContributorsFragment
import com.kotlinmoxysample.controller.contributors.ContributorsPresenter
import dagger.Subcomponent

/**
 * Created by Valentyn on 9/26/17.
 */
@Subcomponent(modules = arrayOf(ContributorsModule::class))
@ForContributors
interface ContributorsComponent {
    fun inject(fragment: ContributorsFragment)

    fun presenterContributors(): ContributorsPresenter
}