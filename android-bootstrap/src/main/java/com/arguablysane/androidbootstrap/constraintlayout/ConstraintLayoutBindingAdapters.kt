package com.arguablysane.androidbootstrap.constraintlayout

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter

object ConstraintLayoutBindingAdapters {

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