package io.github.turskyi.lottieanimationexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


@SuppressLint("ClickableViewAccessibility")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isStarted = false

        animViewFlight.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animViewFlight.resumeAnimation()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    isStarted = true
                    animViewFlight.pauseAnimation()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}