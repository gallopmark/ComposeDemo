package com.pony.compose.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pony.compose.base.BaseFragment
import com.pony.compose.common.CommonHorizontalSpacer
import com.pony.compose.common.CommonTextButton
import com.pony.compose.navi.AnimatedNavHostSampleFragment
import com.pony.compose.paging.PagingSampleFragment
import com.pony.compose.ui.custom.CustomViewPaintFragment
import com.pony.compose.ui.frame.FrameActivity
import com.pony.compose.ui.theme.DarkModeActivity

/**
 *Created by pony on 2022/8/3
 *Description->
 */
class SampleMainFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            CommonTextButton(onClick = {
                context.startActivity(Intent(context, DarkModeActivity::class.java))
            }, text = "Dark Mode")
            CommonHorizontalSpacer()
            CommonTextButton(onClick = {
                FrameActivity.navigate(context, AnimatedNavHostSampleFragment::class.java.canonicalName, "AnimatedNavHost", showTitleBar = false)
            }, text = "AnimatedNavHost")
            CommonHorizontalSpacer()
            CommonTextButton(onClick = {
                FrameActivity.navigate(
                    context,
                    PagingSampleFragment::class.java.canonicalName,
                    "PagingSample"
                )
            }, text = "Paging")
            CommonHorizontalSpacer()
            CommonTextButton(onClick = {
                FrameActivity.navigate(context, CustomViewPaintFragment::class.java.canonicalName, "CustomViewPaint")
            }, text = "CustomViewPaint")
        }
    }
}