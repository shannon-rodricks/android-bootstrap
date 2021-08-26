package com.arguablysane.androidbootstrap.constraintlayout

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter

object ConstraintLayoutBindingAdapters {

    /**
     * Set the aspect ratio for a view within a ConstraintLayout
     * @param ratio The desired aspect ratio (width/height)
     */
    @JvmStatic
    @BindingAdapter("constraint_ratio")
    fun View.setConstraintRatio(ratio: Float) {
        val constraintLayout = this.parent as? ConstraintLayout
        if(constraintLayout != null) {
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)
            constraintSet.setDimensionRatio(this.id, ratio.toString())
            constraintSet.applyTo(constraintLayout)
        }
    }

}