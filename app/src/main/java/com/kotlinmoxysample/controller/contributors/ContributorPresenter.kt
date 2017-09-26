package com.kotlinmoxysample.controller.contributors

import com.arellomobile.mvp.InjectViewState
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import com.kotlinmoxysample.R
import com.kotlinmoxysample.db.BaseDao
import com.kotlinmoxysample.controller.BaseRxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Valentyn on 9/19/17.
 */
@InjectViewState
class ContributorPresenter(val baseDao: BaseDao?, val api: Api) : BaseRxPresenter<ContributorView>() {

    fun loadContributor(contributor : Contributor?) {
        if(contributor == null) { return }

        if(contributor.login.isNullOrEmpty()) {
            showFromDb(contributor.id)
            return
        }

        val d = api.getContributor(contributor.login)
                .doOnNext { baseDao?.put(it) }
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
                            showFromDb(contributor.id)
                        }
                )
        mDisposable?.add(d)
    }

    private fun showFromDb(id : Long?) {
        if(id == null)  {
            // TODO show 'try again view'
            view?.toast(R.string.err_something_wrong)
            return
        }

        val contributors = baseDao?.getById<Contributor>(id)
        Timber.d("Contributors from db $contributors")
        if(contributors != null) {
            viewState.showContributor(contributors)
        } else {
            view?.toast(R.string.err_something_wrong)
        }
    }
}