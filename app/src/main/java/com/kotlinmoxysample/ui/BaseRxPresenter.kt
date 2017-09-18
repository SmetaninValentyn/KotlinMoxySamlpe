package com.kotlinmoxysample.ui

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Valentyn on 9/18/17.
 */
abstract class BaseRxPresenter<V : MvpView> : MvpPresenter<V>() {

    protected var mDisposable: CompositeDisposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mDisposable = CompositeDisposable()
    }

    override fun detachView(view: V) {
        super.detachView(view)
        mDisposable?.dispose()
    }
}