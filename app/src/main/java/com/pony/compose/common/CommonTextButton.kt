package com.pony.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.R
import com.pony.compose.extension.green200
import com.pony.compose.extension.white

/**
 *Created by pony on 2022/6/22
 *Description->通用TextButton
 */
@Composable
fun CommonTextButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    TextButton(
        onClick, modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = if (isSystemInDarkTheme()) green200 else colorResource (id = R.color.purple_700))
    ) {
        Text(text = text, color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}