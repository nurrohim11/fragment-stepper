package com.rohimdev.stepper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.Toast
import com.rohimdev.fragmentstepper.FragmentStepper
import com.rohimdev.fragmentstepper.StepsManager

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener, StepsManager {

    lateinit var stepper: FragmentStepper
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepper = findViewById(R.id.stepper)
        progressBar = findViewById(R.id.progress_bar)

        stepper.setParentActivity(this)
        stepper.stepsChangeListener = object : FragmentStepper.StepsChangeListener {
            override fun onStepChanged(stepNumber: Int) {
                Toast.makeText(this@MainActivity, "Page changed", Toast.LENGTH_SHORT).show()
                progressBar.progress = stepper.currentItem
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getStep(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment.newInstance("Test", "Masuk")
            else -> FirstFragment()
        }
    }

    override fun onFragmentInteraction() {
        stepper.goToNexStep()
    }

    override fun onBackPressed() {
        if (stepper.isLastStep()) super.onBackPressed() else stepper.goToPreviousStep()
    }
}
