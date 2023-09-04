package com.pony.compose.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *Created by pony on 2022/6/20
 *Description->通用标题组件
 */
@Composable
fun TitleComponent(title: String?) {
    Text(
        text = title ?: "",
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W900,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}