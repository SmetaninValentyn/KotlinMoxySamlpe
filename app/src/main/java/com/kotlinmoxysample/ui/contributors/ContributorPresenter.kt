package com.kotlinmoxysample.ui.contributors

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
 * Created by Valentyn on 9/19/17.
 */
@InjectViewState
class ContributorPresenter : BaseRxPresenter<ContributorView>() {

    fun loadContributor(contributor : Contributor?) {
        if(contributor == null) {}

        if(contributor?.login.isNullOrEmpty()) {
            showFromDb(contributor?.id)
            return
        }

        val d = RestClient().createService(Api::class.java)
                .getContributor(contributor?.login)
                .doOnNext { BaseDao().put(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.showProgress(false)
                            viewState.showContributor(it)
                            Timber.d("Contributor $it")
                        },

                        onError = {
                            view?.showProgress(false)
                            showFromDb(contributor?.id)
                        }
                )
        view?.showProgress(true)
        mDisposable?.add(d)
    }

    private fun showFromDb(id : Long?) {
        if(id == null)  {
            // TODO show 'try again view'
            view?.toast(R.string.err_something_wrong)
            return
        }

        val contributors = BaseDao().getById<Contributor>(id)
        Timber.d("Contributors from db $contributors")
        if(contributors != null) {
            viewState.showContributor(contributors)
        } else {
            view?.toast(R.string.err_something_wrong)
        }
    }
}