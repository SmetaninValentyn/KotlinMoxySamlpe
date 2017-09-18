package com.kotlinmoxysample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by Valentyn on 9/18/17.
 */
class FragmentBackStack(var fragmentManager: FragmentManager,
                        private var fragmentContainerID: Int) {

    /**
     * Set new fragment
     *
     * @param fragment       for **replace**, or **push** to stack
     * @param addToBackStack set `true` for push in stack
     */
    @Synchronized private operator fun set(fragment: Fragment, addToBackStack: Boolean) {
        if (!addToBackStack) {
            // checking for baseActivity is alive
            val activity = fragment.activity
            if (!activity.isDestroyed && !activity.isFinishing) {
                clear()
            }

            fragmentManager.beginTransaction()
                    ?.replace(fragmentContainerID, fragment, fragment.javaClass.simpleName)
                    ?.setTransition(FragmentTransaction.TRANSIT_NONE)?.commit()
        } else {
            fragmentManager.beginTransaction()?.replace(fragmentContainerID, fragment)
                    ?.setTransition(FragmentTransaction.TRANSIT_NONE)
                    ?.addToBackStack(fragment.javaClass.simpleName)?.commit()
        }
    }

    /**
     * Add new fragment
     *
     * @param fragment for **add** to stack
     */
    fun push(fragment: Fragment) {
        set(fragment, true)
    }

    /**
     * Set new fragment
     *
     * @param fragment for **replace** and CLEAR stack
     */
    fun replace(fragment: Fragment) {
        set(fragment, false)
    }

    /**
     * Get fragment name
     *
     * @return fragment tag
     */
    fun peek(): String? {
        if (getStackEntryCount() > 0) {
            return fragmentManager.getBackStackEntryAt(getStackEntryCount() - 1)?.name
        } else {
            val fragment = fragmentManager.findFragmentById(fragmentContainerID)

            return fragment?.tag ?: ""
        }
    }

    fun getTopFragment(): Fragment? {
        return fragmentManager.findFragmentById(fragmentContainerID)
    }

    @Synchronized
    fun pop() {
        fragmentManager.popBackStack()
    }

    /**
     * Remove [Main] fragment **(only one and from his container)**
     *
     * @param fragment root fragment
     */
    @Synchronized
    fun removeRoot(fragment: Fragment) {
        fragmentManager.beginTransaction()?.remove(fragment)?.commit()
    }

    /**
     * Clear all fragments except [Main] fragment
     */
    @Synchronized
    fun clear(): Boolean? {
        var result: Boolean?
        try {
            result = fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (ignored: IllegalStateException) {
            result = false
        }

        return result
    }

    /**
     * Remove all fragments above (or with) specified fragment
     *
     * @param inclusive true if specified fragment should be popped
     */
    @Synchronized
    fun raise(fragmentTag: String?, inclusive: Boolean): Boolean {
        var result: Boolean
        try {
            result = fragmentTag != null && fragmentManager.popBackStackImmediate(fragmentTag, if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0)
        } catch (ignored: IllegalStateException) {
            result = false
        }

        return result

    }

    operator fun contains(fragmentTag: String?): Boolean {
        if (fragmentTag != null) {
            val count = getStackEntryCount()

            for (entry in 0 until count) {
                if (fragmentTag == fragmentManager.getBackStackEntryAt(entry)?.name) {
                    return true
                }
            }
        }

        return false
    }

    fun getStackEntryCount(): Int {
        return fragmentManager.backStackEntryCount
    }

    class Retainer : Fragment() {
        // data object we want to retain
        private var backStack: FragmentBackStack? = null

        // this method is only called once for this fragment
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // retain this fragment
            retainInstance = true
        }

        /**
         * Set back stack
         *
         * @param backStack **set** for retain. As rule called in Activity::onDestroy()
         */
        fun retain(backStack: FragmentBackStack?) {
            this.backStack = backStack
        }

        /**
         * Get retained back stack
         *
         * @param fragmentManager **set** fragment manager from current activity
         * @return retained stack
         */
        fun restore(fragmentManager: FragmentManager): FragmentBackStack? {
            backStack?.fragmentManager = fragmentManager
            return backStack
        }

        companion object {

            fun newInstance(): Retainer {
                return Retainer()
            }
        }
    }
}