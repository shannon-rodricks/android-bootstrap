package com.arguablysane.androidbootstrap.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import com.arguablysane.androidbootstrap.animation.SimpleAnimationListener

object ViewBindingAdapters {

    /**
     * Change the visibility of a view, with animation
     */
    @JvmStatic
    @BindingAdapter(
        value = [
            "visibility",
            "visibility_visible_animation",
            "visibility_gone_animation",
            "visibility_invisible_animation"
        ],
        requireAll = false
    )
    fun View.setVisibility(
        visibility: Int,
        visibilityAnimationResIdVisible: Int? = null,
        visibilityAnimationResIdGone: Int? = null,
        visibilityAnimationResIdInvisible: Int? = null
    ) {
        // If the value is already set, don't do anything
        if (this.visibility == visibility) {
            return
        }

        // Create the animation, if present
        val animation: Animation? = when (visibility) {
            View.VISIBLE -> visibilityAnimationResIdVisible
                .takeIf { it != 0 }
                ?.run {
                    try {
                        AnimationUtils.loadAnimation(context, this).apply {
                            setAnimationListener(
                                SimpleAnimationListener(
                                    onAnimationStart = {
                                        this@setVisibility.visibility = View.VISIBLE
                                    }
                                )
                            )
                        }
                    } catch (ex: Exception) {
                        null
                    }
                }

            View.GONE -> visibilityAnimationResIdGone
                .takeIf { it != 0 }
                ?.run {
                    try {
                        AnimationUtils.loadAnimation(context, this).apply {
                            setAnimationListener(
                                SimpleAnimationListener(
                                    onAnimationEnd = {
                                        this@setVisibility.visibility = View.GONE
                                    }
                                )
                            )
                        }
                    } catch (ex: Exception) {
                        null
                    }
                }

            else -> visibilityAnimationResIdInvisible
                .takeIf { it != 0 }
                ?.run {
                    try {
                        AnimationUtils.loadAnimation(context, this).apply {
                            setAnimationListener(
                                SimpleAnimationListener(
                                    onAnimationEnd = {
                                        this@setVisibility.visibility = View.INVISIBLE
                                    }
                                )
                            )
                        }
                    } catch (ex: Exception) {
                        null
                    }
                }
        }

        if (animation != null) {
            startAnimation(animation)
        } else {
            this.visibility = visibility
        }

    }

    @JvmStatic
    @BindingAdapter(
        value = [
            "visibleGone", "visibility_visible_animation", "visibility_gone_animation"
        ], requireAll = false
    )
    fun View.setVisibleGone(
        visible: Boolean,
        animationVisible: Int? = null,
        animationGone: Int? = null
    ) {
        if (visible && visibility == View.VISIBLE) {
            return
        }

        if (!visible && visibility == View.GONE) {
            return
        }

        setVisibility(
            visibility = if (visible) View.VISIBLE else View.GONE,
            visibilityAnimationResIdVisible = animationVisible,
            visibilityAnimationResIdGone = animationGone
        )
    }

}