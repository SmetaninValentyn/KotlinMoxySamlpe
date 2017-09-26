package com.kotlinmoxysample.controller.contributors

import com.kotlingithubapi.network.Api
import com.kotlinmoxysample.db.ContributorsDao
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull

/**
 * Created by Valentyn on 9/26/17.
 */
@Module
class ContributorsModule {

    @Provides
    @NotNull
    @ForContributors
    fun providesContributorsPresenter(boxStore: BoxStore, api: Api): ContributorsPresenter
            = ContributorsPresenter(ContributorsDao(boxStore), api)

    @Provides
    @NotNull
    @ForContributors
    fun providesContributorPresenter(boxStore: BoxStore, api: Api): ContributorPresenter
            = ContributorPresenter(ContributorsDao(boxStore), api)

}