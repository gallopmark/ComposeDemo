package com.pony.compose.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *Created by pony on 2022/7/20
 *Description->
 */
@Composable
fun CommonHorizontalSpacer(height: Dp = 4.dp){
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    )
}