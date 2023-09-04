package com.pony.compose.ui.custom

import android.graphics.PointF
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.tiktokRed
import com.pony.compose.extension.toPx

/**
 *Created by pony on 2022/9/2
 *Description->
 */
class CustomViewPaintFragment : BaseFragment() {
    @Composable
    override fun CreateContentView() {
        CustomDrawableViewComponent()
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun CustomDrawableViewComponent() {
        val paths = remember {
            mutableStateListOf<PointF>()
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                when (it.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        paths += PointF(it.x, it.y)
                        true
                    }
                    else -> false
                }
            }, content = {
            Canvas(modifier = Modifier) {
                val path = Path()
                for (p in paths) {
                    path.lineTo(p.x, p.y)
                    path.moveTo(p.x, p.y)
                }
                drawPath(path, color = tiktokRed, style = Stroke(width = 4.dp.toPx, join = StrokeJoin.Round))
            }
        })
    }
}