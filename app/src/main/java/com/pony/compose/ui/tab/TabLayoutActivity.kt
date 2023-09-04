package com.pony.compose.ui.tab

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pony.compose.base.BaseActivity
import com.pony.compose.extension.transparent

/**
 *Created by pony on 2022/7/11
 *Description->
 */
class TabLayoutActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        SimpleTabRowComponent()
    }
}

@Composable
private fun SimpleTabRowComponent() {
    val selectedTab = remember {
        mutableStateOf(0)
    }
    val tabsText = arrayOf("当日", "5日", "20日")
    TabRow(selectedTabIndex = selectedTab.value,
        modifier = Modifier
            .width(250.dp)
            .height(50.dp),
             contentColor = transparent, backgroundColor = transparent, indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab.value])
                    .width(48.dp).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                        .background(Color.White, shape = RoundedCornerShape(12.dp)),
                )
            }
        }, divider = {
            Divider(color = Color.Transparent)
        }) {
        tabsText.forEachIndexed { index, s ->
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(48.dp).fillMaxHeight()
                    .zIndex(1f)
                    .background(color = if (selectedTab.value == index) Color(0xFFFDEDEC) else Color(0xFFF5F6FA), shape = RoundedCornerShape(12.dp))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                selectedTab.value = index
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = s,
                    fontSize = if (selectedTab.value == index) 20.sp else 18.sp,
                    color = if (selectedTab.value == index) Color(0xff333333) else Color(0xff666666),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}