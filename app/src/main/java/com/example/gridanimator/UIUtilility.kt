package com.example.gridanimator

import android.content.Context
import android.util.TypedValue

object UIUtilility {

    fun dpToPixels(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics)
    }
}