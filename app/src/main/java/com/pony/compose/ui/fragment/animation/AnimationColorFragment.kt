package com.pony.compose.ui.fragment.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.green700
import com.pony.compose.extension.tiktokBlue
import com.pony.compose.extension.tiktokRed
import com.pony.compose.extension.yellow

/**
 *Created by pony on 2022/7/15
 *Description->
 */
class AnimationColorFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        AnimationColorComponent()
    }
}

@Composable
private fun AnimationColorComponent() {
    val currentColor by remember {
        mutableStateOf(tiktokRed)
    }
    val transition = updateTransition(targetState = currentColor, label = "")
    val color by transition.animateColor(transitionSpec = {
        TweenSpec(durationMillis = 1000)
    }, label = "ColorAnimation") { state ->
        when (state) {
            tiktokRed -> tiktokBlue
            tiktokBlue -> green700
            green700 -> yellow
            else -> tiktokRed
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    )
}