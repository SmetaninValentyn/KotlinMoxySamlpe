package com.kotlinmoxysample.db

import com.kotlinmoxysample.KotlinMoxyApplication
import timber.log.Timber

/**
 * Created by Valentyn on 9/21/17.
 */
open class BaseDao {

    var boxStore = KotlinMoxyApplication.instance.boxStore

    inline fun <reified T> put(entity: T?) {
        if(entity == null) return
        val box  = boxStore.boxFor(T::class.java)
        box?.put(entity)
        Timber.d("Saving entity $entity")
    }

    inline fun <reified T> put(list: List<T>?) {
        if(list == null) return
        val box  = boxStore.boxFor(T::class.java)
        box?.put(list)
        Timber.d("Saving list $list")
    }

    inline fun <reified T> getById(id : Long) : T? {
        val entity = boxStore.boxFor(T::class.java)?.get(id)
        Timber.d("Getting entity $entity")
        return entity
    }

    inline fun <reified T> getAll() : List<T>? {
        val all = boxStore.boxFor(T::class.java)?.all
        Timber.d("Getting collection $all")
        return all
    }

    inline fun <reified T> remove(list: List<T>?) {
        if(list == null) return
        val box  = boxStore.boxFor(T::class.java)
        box?.remove(list)
        Timber.d("Saving list $list")
    }

    inline fun <reified T> removeAll() {
        boxStore.boxFor(T::class.java).removeAll()
    }

}