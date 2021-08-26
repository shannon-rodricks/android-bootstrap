package com.arguablysane.androidbootstrap.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.databinding.BindingAdapter
import com.arguablysane.androidbootstrap.animation.SimpleAnimationListener

object ViewBindingAdapters {

    /**
     * Change the visibility of a view, with optional animations
     * @param visibility The visibility to set. One of [View.VISIBLE], [View.INVISIBLE], [View.GONE]
     * @param visibilityAnimationResIdVisible Optional animation to run when the visibility is set to [View.VISIBLE]
     * @param visibilityAnimationResIdGone Optional animation to run when the visibility is set to [View.GONE]
     * @param visibilityAnimationResIdInvisible Optional animation to run when the visibility is set to [View.INVISIBLE]
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
        @AnimRes visibilityAnimationResIdVisible: Int? = null,
        @AnimRes visibilityAnimationResIdGone: Int? = null,
        @AnimRes visibilityAnimationResIdInvisible: Int? = null
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

    /**
     * Toggle animation between [View.VISIBLE] and [View.GONE]
     * @param visible Whether the view should be visible or not
     * @param animationVisible Optional animation resource id to run when [visible] is true
     * @param animationGone Optional animation resource id to run when [visible] is false
     */
    @JvmStatic
    @BindingAdapter(
        value = [
            "visibleGone", "visibility_visible_animation", "visibility_gone_animation"
        ], requireAll = false
    )
    fun View.setVisibleGone(
        visible: Boolean,
        @AnimRes animationVisible: Int? = null,
        @AnimRes animationGone: Int? = null
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