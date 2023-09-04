package com.pony.compose.common

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.R
import com.pony.compose.extension.black333
import com.pony.compose.extension.white

/**
 *Created by pony on 2022/6/17
 *Description->通用Appbar
 */
const val NO_NAVIGATION_ICON = -1  //去除返回键标识

@Composable
fun AppToolbar(
    title: String?,
    @DrawableRes navigationIcon: Int = R.drawable.ic_arrow_back,
    actions: @Composable RowScope.() -> Unit = {},
    backAction: (() -> Unit)? = null
) {
    val enableDarkMode = isSystemInDarkTheme()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (enableDarkMode) white else black333,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, modifier = Modifier
            .fillMaxWidth(), navigationIcon = {
            if (navigationIcon != NO_NAVIGATION_ICON) {
                val context = LocalContext.current
                IconButton(onClick = {
                    if (backAction != null) {
                        backAction.invoke()
                    } else {
                        if (context is Activity) {
                            context.finish()
                        }
                    }
                }) {
                    Image(
                        painter = painterResource(id = navigationIcon),
                        contentDescription = "navigation",
                        colorFilter = ColorFilter.tint(color = if (enableDarkMode) white else black333)
                    )
                }
            }
        }, actions = actions
    )
}