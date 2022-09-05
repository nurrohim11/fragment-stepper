package com.rohimdev.fragmentstepper

import androidx.fragment.app.Fragment

interface StepsManager {
    fun getCount(): Int
    fun getStep(position: Int): Fragment
}