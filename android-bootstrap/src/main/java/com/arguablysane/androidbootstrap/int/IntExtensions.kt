package com.arguablysane.androidbootstrap.int

import android.content.res.Resources
import android.util.TypedValue

object IntExtensions {

    /**
     * Get the SP value for an integer
     */
    fun Int.sp(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }

    /**
     * Get the DP value for an integer
     */
    fun Int.dp(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }

}