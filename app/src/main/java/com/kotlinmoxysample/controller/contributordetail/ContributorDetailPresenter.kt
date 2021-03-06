package com.kotlinmoxysample.controller.contributordetail

import com.arellomobile.mvp.InjectViewState
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.network.Api
import com.kotlinmoxysample.R
import com.kotlinmoxysample.db.BaseDao
import com.kotlinmoxysample.controller.base.BaseRxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Valentyn on 9/19/17.
 */
@InjectViewState
class ContributorDetailPresenter(private val baseDao: BaseDao?, private val api: Api) : BaseRxPresenter<ContributorDetailView>() {

    fun loadContributor(contributor : Contributor?) {
        if(contributor == null) { return }

        viewState.showContributor(contributor)

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