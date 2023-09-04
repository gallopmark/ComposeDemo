package com.pony.compose.ui.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.purple200
import com.pony.compose.extension.white
import com.pony.compose.ui.fragment.animation.AnimateVisibilityFragment
import com.pony.compose.ui.fragment.animation.AnimatedContentFragment
import com.pony.compose.ui.fragment.animation.AnimationColorFragment
import com.pony.compose.ui.fragment.animation.RotateAnimationFragment
import com.pony.compose.ui.frame.FrameActivity

/**
 *Created by pony on 2022/7/15
 *Description->
 */
class ComposeAnimationFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        ComposeAnimationComponent()
    }
}

@Composable
private fun ComposeAnimationComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        TextButton(
            onClick = {
                FrameActivity.navigate(context, RotateAnimationFragment::class.java.canonicalName, title = "RotateAnimation")
            }, modifier = Modifier
                .fillMaxWidth()
                .background(color = purple200)
                .height(50.dp)
        ) {
            Text(text = "RotateAnimation", color = white, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        TextButton(
            onClick = {
                FrameActivity.navigate(context, AnimationColorFragment::class.java.canonicalName, title = "AnimationColor")
            }, modifier = Modifier
                .fillMaxWidth()
                .background(color = purple200)
                .height(50.dp)
        ) {
            Text(text = "AnimationColor", color = white, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        TextButton(
            onClick = {
                FrameActivity.navigate(context, AnimatedContentFragment::class.java.canonicalName, title = "AnimatedContent")
            }, modifier = Modifier
                .fillMaxWidth()
                .background(color = purple200)
                .height(50.dp)
        ) {
            Text(text = "AnimatedContent", color = white, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        TextButton(
            onClick = {
                FrameActivity.navigate(context, AnimateVisibilityFragment::class.java.canonicalName, title = "AnimatedContent")
            }, modifier = Modifier
                .fillMaxWidth()
                .background(color = purple200)
                .height(50.dp)
        ) {
            Text(text = "AnimatedVisibility", color = white, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
    }
}