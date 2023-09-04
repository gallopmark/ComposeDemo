package com.pony.compose.ui.fragment.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pony.compose.base.BaseFragment

/**
 *Created by pony on 2022/7/15
 *Description->
 */
class AnimateVisibilityFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        val visible by remember { mutableStateOf(true) }
        AnimatedVisibility(visible = visible, enter = fadeIn(initialAlpha = 0.4f), exit = fadeOut(animationSpec = tween(durationMillis = 500))) {
            Text(text = "Content to appear/disappear", modifier = Modifier.fillMaxSize().requiredHeight(200.dp))
        }
    }
}