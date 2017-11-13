package com.kotlinmoxysample.controller.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Valentyn on 9/18/17.
 */
abstract class BaseRxPresenter<V : MvpView> : MvpPresenter<V>() {

    protected var view: V? = null
    protected var mDisposable: CompositeDisposable? = null

    override fun attachView(view: V) {
        super.attachView(view)
        this.view = view
        mDisposable = CompositeDisposable()
    }

    override fun detachView(view: V) {
        super.detachView(view)
        mDisposable?.clear()
    }
}