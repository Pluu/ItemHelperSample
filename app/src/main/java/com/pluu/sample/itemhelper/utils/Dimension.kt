package com.pluu.sample.itemhelper.utils

import android.content.Context
import android.util.TypedValue

fun Context.dp2px(dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resources.displayMetrics
    ).toInt()
}