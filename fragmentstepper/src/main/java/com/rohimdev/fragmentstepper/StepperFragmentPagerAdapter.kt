package com.rohimdev.fragmentstepper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class StepperFragmentPagerAdapter(fm: FragmentManager, private var stepsManager: StepsManager): SmartFragmentStatePagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
        return stepsManager.getStep(position)
    }

    override fun getCount(): Int {
        return stepsManager.getCount()
    }
}