package com.pony.compose.paging.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.R
import com.pony.compose.extension.blue
import com.pony.compose.extension.blue700
import com.pony.compose.extension.graySurface

@Composable
fun LoadingContentComponent() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

@Composable
fun ErrorContentComponent(retry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { retry() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_error), contentDescription = "error", modifier = Modifier.size(50.dp))
        Text(text = "加载异常，点我重试", modifier = Modifier.padding(top = 20.dp), fontSize = 18.sp)
    }
}

//滑动到底部加载中
@Composable
fun LoadingFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp), color = blue, strokeWidth = 2.dp)
        Text(text = "数据加载中...", color = graySurface, modifier = Modifier.padding(start = 20.dp), fontSize = 20.sp)
    }
}

/**
 * 加载更多失败
 */
@Composable
fun LoadMoreErrorComponent(retry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                retry()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.TwoTone.Refresh, contentDescription = "load error!", modifier = Modifier.size(30.dp), tint = blue700)
        Text(text = "加载失败，请点击重试！", color = graySurface, fontSize = 18.sp, modifier = Modifier.padding(start = 20.dp))
    }
}