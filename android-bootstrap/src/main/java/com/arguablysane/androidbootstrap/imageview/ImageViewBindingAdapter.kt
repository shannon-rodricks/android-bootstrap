package com.arguablysane.androidbootstrap.imageview

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import java.lang.Exception

/**
 * Binding adapters for
 */
object ImageViewBindingAdapter {

    /**
     * Set a drawable via resource Id
     * @param id resource identifier for the drawable to display
     */
    @JvmStatic
    @BindingAdapter("srcResId")
    fun ImageView.setSourceResourceId(@DrawableRes id: Int) {
        setImageResource(id)
    }

    /**
     *
     */
    @JvmStatic
    @BindingAdapter("tintColorResourceId")
    fun ImageView.setTintResourceId(@ColorRes resourceId: Int?) {
        if(resourceId != null) {
            try {
                setColorFilter(ContextCompat.getColor(context, resourceId))
            } catch (ex: Throwable) {

            }
        }
    }

}