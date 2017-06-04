package com.cdev.achievementview

import android.animation.Animator
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

/**
 * Created by aszabo on 6/1/17.
 */

fun Animator.addCancelListener(action: (Animator) -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationEnd(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationCancel(animation: Animator?) {
            animation?.let { action(it) }
        }

        override fun onAnimationStart(animation: Animator?) {
            // do nothing
        }
    })
}

fun Animator.addEndListener(action: (Animator) -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationEnd(animation: Animator?) {
            animation?.let { action(it) }
        }

        override fun onAnimationCancel(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationStart(animation: Animator?) {
            // do nothing
        }
    })
}

fun Animator.addStartListener(action: (Animator) -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationEnd(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationCancel(animation: Animator?) {
            // do nothing
        }

        override fun onAnimationStart(animation: Animator?) {
            animation?.let { action(it) }
        }
    })
}

fun AlphaAnimation.addEndListener(action: (Animation) -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            // do nothing
        }

        override fun onAnimationEnd(animation: Animation?) {
            animation?.let { action(it) }
        }

        override fun onAnimationStart(animation: Animation?) {
            // do nothing
        }
    })
}

fun View.updateWidth(width: Int) {
    val layoutParamsUpdate = layoutParams
    layoutParamsUpdate.width = width
    layoutParams = layoutParamsUpdate
}

fun View.setShapeColor(color: Int) {
    val leftGradientDrawable: GradientDrawable = this.background as GradientDrawable
    leftGradientDrawable.mutate()
    leftGradientDrawable.setColor(color)
}
