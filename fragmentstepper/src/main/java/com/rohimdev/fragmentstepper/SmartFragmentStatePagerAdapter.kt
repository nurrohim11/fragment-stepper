package com.rohimdev.fragmentstepper

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class SmartFragmentStatePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var registeredFragments = SparseArray<Fragment>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, item)
    }

    fun getRegisteredFragment(postion: Int): Fragment
    {
        return registeredFragments.get(postion)
    }
}