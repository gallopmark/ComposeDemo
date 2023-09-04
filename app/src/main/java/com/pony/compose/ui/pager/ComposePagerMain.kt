package com.pony.compose.ui.pager

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pony.compose.base.BaseActivity
import com.pony.compose.common.CommonTextButton

/**
 *Created by pony on 2022/6/22
 *Description->pager首页
 */
class ComposePagerMain : BaseActivity() {

    override fun requireTitle(): String = "Compose Pager Main"

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            CommonTextButton(onClick = {
                startActivity(Intent(this@ComposePagerMain, HorizontalPagerBasicSample::class.java))
            }, text = "HorizontalPagerBasicSample")
        }
    }
}