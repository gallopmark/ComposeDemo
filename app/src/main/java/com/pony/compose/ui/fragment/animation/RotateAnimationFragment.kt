package com.pony.compose.ui.fragment.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.orange200

/**
 *Created by pony on 2022/7/14
 *Description->旋转动画
 */
class RotateAnimationFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f, targetValue = 360f,
                animationSpec = infiniteRepeatable(animation = tween(3000, easing = FastOutSlowInEasing))
            )
            Canvas(modifier = Modifier.size(200.dp), onDraw = {
                rotate(rotation) {
                    drawRect(color = orange200)
                }
            })
        }
    }
}