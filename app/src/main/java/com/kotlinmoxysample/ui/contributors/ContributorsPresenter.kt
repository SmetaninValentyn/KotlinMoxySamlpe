package com.kotlinmoxysample.ui.contributors

import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import com.kotlinmoxysample.R
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

    fun onContributorClick(contributor: Contributor?) {
        viewState.toast(contributor.toString())
    }

    fun loadContributors() {
        viewState.showProgress(true)
        val d = RestClient().createService(Api::class.java)
                .repoContributors("square", "retrofit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            viewState.showProgress(false)
                            viewState.showContributors(it)
                            Timber.d("Contributors $it")
                        },

                        onError = {
                            viewState.showProgress(false)
                            viewState.toast(R.string.err_something_wrong)

                            Timber.e("Contributors ${it.message}")
                        }
                )
        mDisposable?.add(d)
    }

}