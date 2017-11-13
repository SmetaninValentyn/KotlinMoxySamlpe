package com.kotlingithubapi.di.contributordetail

import com.kotlingithubapi.network.Api
import com.kotlinmoxysample.controller.contributordetail.ContributorDetailPresenter
import com.kotlinmoxysample.db.ContributorsDao
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull

/**
 * Created by Valentyn on 9/26/17.
 */
@Module
class ContributorDetailModule {

    @Provides
    @ForContributorDetail
    @NotNull
    fun providesContributorPresenter(boxStore: BoxStore, api: Api): ContributorDetailPresenter =
            ContributorDetailPresenter(ContributorsDao(boxStore), api)

}