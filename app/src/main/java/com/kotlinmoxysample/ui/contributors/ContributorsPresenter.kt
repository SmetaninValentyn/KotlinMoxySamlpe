package com.kotlinmoxysample.ui.contributors

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import com.kotlinmoxysample.R
import com.kotlinmoxysample.db.BaseDao
import com.kotlinmoxysample.db.ContributorsDao
import com.kotlinmoxysample.ui.BaseRxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Valentyn on 9/18/17.
 */
@InjectViewState
class ContributorsPresenter : BaseRxPresenter<ContributorsView>() {

    fun onContributorClicked(contributor: Contributor?, avatarView : View? = null) {
        view?.onContributorClick(contributor, avatarView)
    }

    fun loadContributors() {
        viewState.showProgress(true)
        val d = RestClient().createService(Api::class.java)
                .repoContributors("square", "retrofit")
                .doOnNext { BaseDao().put(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            viewState.showProgress(false)
                            viewState.showContributors(it)
                            Timber.d("Contributors $it")
                        },

                        onError = {
                            Timber.e("Contributors ${it.message}")
                            viewState.showProgress(false)

                            val contributors = ContributorsDao().getContributors()
                            Timber.d("Contributors from db $contributors")
                            if(contributors != null) {
                                viewState.showContributors(contributors)
                            } else {
                                view?.toast(R.string.err_something_wrong)
                            }
                        }
                )
        mDisposable?.add(d)
    }

}