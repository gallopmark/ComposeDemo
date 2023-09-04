package com.pony.compose.ui.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.pony.compose.common.AppToolbar
import com.pony.compose.theme.AppCommonTheme
import kotlinx.coroutines.launch

/**
 *Created by pony on 2022/6/22
 *Description-> HorizontalPagerBasicSample
 */
class HorizontalPagerBasicSample : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCommonTheme {
                HorizontalPagerSample()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
private fun HorizontalPagerSample() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        AppToolbar(title = "HorizontalPagerBasicSampleHorizontalPagerBasicSample")
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val pagerState = rememberPagerState()
            // Display 10 items
            HorizontalPager(
                count = 10,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = 32.dp),
                // Add some horizontal spacing between items
                itemSpacing = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
            ) { page ->
                PagerSampleItem(
                    page = page,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            ActionsRow(
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ActionsRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(modifier) {
        val scope = rememberCoroutineScope()
        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            },
            modifier = Modifier.weight(1.0f)
        ) {
            Text(text = "第一页")
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
            modifier = Modifier.weight(1.0f)
        ) {
            Text(text = "上一页")
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
            modifier = Modifier.weight(1.0f)
        ) {
            Text(text = "下一页")
        }

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            },
            modifier = Modifier.weight(1.0f)
        ) {
            Text(text = "最后一页")
        }
    }
}