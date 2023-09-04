package com.pony.compose.extension

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp

/**
 *Created by pony on 2022/7/20
 *Description->
 */
val Dp.toPx get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)