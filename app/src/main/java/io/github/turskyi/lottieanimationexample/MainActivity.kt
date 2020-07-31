package io.github.turskyi.lottieanimationexample

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("ClickableViewAccessibility")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        animViewSpeed.speed = (seekBarSpeed.progress.toFloat() / 50) + 1
        animViewPingPong.playAnimation()
        animViewOnCompletion.progress = 1f
    }

    private fun initListener() {
        onClick()
        onTap()
        onSpeedChange()
        playBackwards()
        forwardBackwardLoop()
        btnStart.setOnClickListener {
            animViewOnCompletion.playAnimation()
        }
        animViewOnCompletion.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.e("Animation:", "start")
            }

            override fun onAnimationEnd(animation: Animator) {
                Log.e("Animation:", "end")
                Toast.makeText(applicationContext, "Animation Completed", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.e("Animation:", "cancel")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.e("Animation:", "repeat")
            }
        })
    }

    private fun forwardBackwardLoop() {
        animViewPingPong.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.e("Animation:", "start")
            }

            override fun onAnimationEnd(animation: Animator) {
                Log.e("Animation:", "end")
                if (animViewPingPong.speed > 0) {
                    animViewPingPong.speed = -1f
                    animViewPingPong.playAnimation()
                } else {
                    animViewPingPong.speed = 1f
                    animViewPingPong.playAnimation()
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.e("Animation:", "cancel")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.e("Animation:", "repeat")
            }
        })
    }

    private fun playBackwards() {
        btnReverse.setOnClickListener {
            if (animViewBackward.speed > 0) {
                btnReverse.text = getString(R.string.play_forward)
                animViewBackward.speed = -1f
            } else {
                animViewBackward.speed = 1f
                btnReverse.text = resources.getString(R.string.play_backward)
            }
        }
    }

    private fun onSpeedChange() {
        seekBarSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                run {
                    animViewSpeed.speed = (progress.toFloat() / 50) + 1
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    private fun onClick() {
        animViewClick.setOnClickListener {
            animViewClick.playAnimation()
        }
    }

    private fun onTap() {
        animViewFlight.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animViewFlight.resumeAnimation()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    animViewFlight.pauseAnimation()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}