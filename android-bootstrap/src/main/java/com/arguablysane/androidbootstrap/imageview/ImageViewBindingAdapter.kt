package com.arguablysane.androidbootstrap.imageview

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import java.lang.Exception

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("srcResId")
    fun ImageView.setSourceResourceId(id: Int) {
        setImageResource(id)
    }

    @JvmStatic
    @BindingAdapter("tintColorResourceId")
    fun ImageView.setTintResourceId(resourceId: Int?) {
        if(resourceId != null) {
            try {
                setColorFilter(ContextCompat.getColor(context, resourceId))
            } catch (ex: Throwable) {

            }
        }
    }

}