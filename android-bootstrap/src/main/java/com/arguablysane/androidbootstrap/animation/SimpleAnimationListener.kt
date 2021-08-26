package com.arguablysane.androidbootstrap.animation

import android.view.animation.Animation

/**
 * An [Animation.AnimationListener] subclass that enables selective handling.
 *
 * @property onAnimationStart A unit that should be run when the animation starts
 * @property onAnimationRepeat A unit that should be run when the animation repeats
 * @property onAnimationEnd A unit that should be run when the animation ends
 */
open class SimpleAnimationListener(
    private val onAnimationStart: (() -> Unit)? = null,
    private val onAnimationEnd: (() -> Unit)? = null,
    private val onAnimationRepeat: (() -> Unit)? = null
) : Animation.AnimationListener {

    override fun onAnimationStart(animation: Animation?) {
        onAnimationStart?.invoke()
    }

    override fun onAnimationEnd(animation: Animation?) {
        onAnimationEnd?.invoke()
    }

    override fun onAnimationRepeat(animation: Animation?) {
        onAnimationRepeat?.invoke()
    }
}