## Add it to your project

In your project root build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and in the app or module build.gradle:

```gradle
dependencies {
    
}
```

## How to use

#### Step 1: add it to your layout
```xml
<com.rygelouv.fragmentstepper.FragmentStepper
        android:layout_weight="1"
        android:id="@+id/fs_stepper"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
```
#### Step 2: let your activity inherit from `StepsManager` and implements methods
```kotlin
class MainActivity : AppCompatActivity(), StepsManager{
    ...
    
    override fun getCount(): Int {
            return 3
        }
    
    override fun getStep(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment.newInstance("Boom", "Baam")
            else -> FirstFragment()
        }
    }
}
```
#### Step 3: configure stepper
```kotlin
stepper = findViewById(R.id.stepper)
stepper.setParentActivity(this)
stepper.stepsChangeListener = object : FragmentStepper.StepChangeListener {
    override fun onStepChanged(stepNumber: Int) {
        Toast.makeText(this@MainActivity, "Page changed", Toast.LENGTH_SHORT).show()
        // Do whatever you want here
    }
} 
```
#### Step 4: configure backstack in order to handle navigation
In your activity, override the `onBackPressed()` method and add this code
```kotlin
override fun onBackPressed() {
    if (stepper.isLastStep()) super.onBackPressed() else stepper.goToPreviousStep()
}
```
#### Step 5: there is no step 5. That's it you're all set

## TODO
- Handle animations for transition
- Improve the way of handling backstack
- Profile for memory management