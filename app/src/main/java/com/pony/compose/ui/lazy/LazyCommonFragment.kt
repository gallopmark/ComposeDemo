package com.pony.compose.ui.lazy

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.pony.compose.base.BaseFragment
import com.pony.compose.common.CommonHorizontalSpacer
import com.pony.compose.common.CommonTextButton
import com.pony.compose.ui.frame.FrameActivity
import com.pony.compose.ui.lazy.grid.LazyHorizontalGridFragment
import com.pony.compose.ui.lazy.grid.LazyVerticalGridFragment

/**
 *Created by pony on 2022/7/20
 *Description->LazyColumn、LazyRow、LazyHorizontalGrid,LazyVerticalGrid
 */
class LazyCommonFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            CommonTextButton(onClick = {
                FrameActivity.navigate(context, LazyHorizontalGridFragment::class.java.canonicalName, "LazyHorizontalGrid")
            }, text = "LazyHorizontalGrid")
            CommonHorizontalSpacer()
            CommonTextButton(onClick = {
                FrameActivity.navigate(context, LazyVerticalGridFragment::class.java.canonicalName, "LazyVerticalGrid")
            }, "LazyVerticalGrid")
            CommonHorizontalSpacer()
            CommonTextButton(onClick = {
                FrameActivity.navigate(context, LazyColumnFragment::class.java.canonicalName, "LazyColumn")
            }, text = "LazyColumn")
        }
    }
}