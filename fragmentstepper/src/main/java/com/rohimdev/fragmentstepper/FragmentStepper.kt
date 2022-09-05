package com.rohimdev.fragmentstepper

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class FragmentStepper: ViewPager {

    lateinit var pagerAdapter: StepperFragmentPagerAdapter
    lateinit var stepsChangeListener: StepsChangeListener
    private lateinit var activity: AppCompatActivity

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    private fun init()
    {
        setCustomScroller()
        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                stepsChangeListener.onStepChanged(position)
            }
        })
    }

    fun goToNexStep() {
        currentItem += 1
    }

    fun goToPreviousStep() {
        currentItem -= 1
    }

    fun isLastStep(): Boolean = currentItem == 0

    fun setParentActivity(activity: AppCompatActivity)
    {
        if (activity is StepsManager) {
            this.activity = activity
            pagerAdapter = StepperFragmentPagerAdapter(activity.supportFragmentManager, activity)
            adapter = pagerAdapter
        }
        else throw ActivityNotStepsManagerException()
    }

    private fun setCustomScroller() {
        try {
            val viewPager= ViewPager::class.java
            val scroller = viewPager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, CustomScroller(context))
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class CustomScroller(context: Context?) : Scroller(context, DecelerateInterpolator()) {
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 350)
        }
    }

    interface StepsChangeListener{
        fun onStepChanged(stepNumber: Int)
    }
}
