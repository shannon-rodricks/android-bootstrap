package com.arguablysane.androidbootstrap.errorview

import androidx.annotation.DrawableRes

data class ErrorViewState(
    val id: Int? = null,
    @DrawableRes val iconResourceId: Int? = null,
    val title: CharSequence? = null,
    val message: CharSequence? = null,
    val retryBtnLabel: CharSequence? = null
)