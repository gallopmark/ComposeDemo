package com.pony.compose.ui.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *Created by pony on 2022/6/22
 *Description->
 */
@Composable
internal fun PagerSampleItem(
    page: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        // Displays the page index
        Text(
            text = page.toString(),
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color.White, RoundedCornerShape(4.dp))
                .padding(8.dp)
        )
    }
}