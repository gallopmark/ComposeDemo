package com.pony.compose.ui.pager

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pony.compose.R
import com.pony.compose.base.BaseActivity
import com.pony.compose.extension.black333
import com.pony.compose.extension.purple
import com.pony.compose.extension.purple200
import com.pony.compose.extension.white
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *Created by pony on 2022/6/8
 *Description->
 */
@AndroidEntryPoint
@ExperimentalPagerApi
class ComposeViewPagerActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        HorizontalViewPagerComponent()
    }

    override fun requireTitle(): String = "Compose HorizontalPaper"
}

@ExperimentalPagerApi
@Composable
private fun HorizontalViewPagerComponent() {
    val viewModel: NetworkViewModel = hiltViewModel()
    PagerContentScreen(viewModel)
    viewModel.getData()
}

@ExperimentalPagerApi
@Composable
fun PagerContentScreen(viewModel: NetworkViewModel) {
    val state by viewModel.liveData.observeAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is LoadViewState.Loading -> {
                Text(text = "Loading...")
            }
            is LoadViewState.Success -> {
                SuccessContent()
            }
            is LoadViewState.Failure -> {
                Text(text = "页面加载失败")
            }
            else -> {
                Text(text = "暂无数据")
            }
        }
    }
}

private data class TabItem(val label: String, val selectIconId: Int, val unselectIconId: Int)

@ExperimentalPagerApi
@Composable
private fun SuccessContent() {
//    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    val tabItems = listOf(
        TabItem("首页", R.drawable.home_tab_home_pressed, R.drawable.home_tab_home_normal),
        TabItem("直播", R.drawable.home_tab_circle_pressed, R.drawable.home_tab_circle_normal),
        TabItem("智投", R.drawable.home_tab_course_pressed, R.drawable.home_tab_course_normal),
        TabItem("行情", R.drawable.home_tab_quote_pressed, R.drawable.home_tab_quote_normal),
        TabItem("我的", R.drawable.home_tab_mine_pressed, R.drawable.home_tab_mine_normal)
    )
    val pagerState = rememberPagerState()
    val selectState = remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = tabItems.size, state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) { page: Int ->
//            selectState.value
            selectState.value = currentPage
            Log.e("currentPage", "${pagerState.currentPage}")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "我是${tabItems[page].label}页面", color = black333, fontSize = 20.sp)
            }
        }
//        TabRow(selectedTabIndex = selectState.value) {
//            tabItems.forEachIndexed { index, tabItem ->
//                Tab(selected = selectState.value == index, onClick = {
//                    selectState.value = index
//                    scope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                }) {
//
//                }
//            }
//        }
//        TabRow(
//            selectedTabIndex = selectState.value, modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//        ) {
//
//        }
        Spacer(modifier = Modifier.height(10.dp))
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                BottomNavigationItem(selected = selectState.value == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                        selectState.value = index
                    }
                }, icon = {
                    Image(
                        painter = painterResource(id = if (selectState.value == index) tabItem.selectIconId else tabItem.unselectIconId),
                        contentDescription = "首页",
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(24.dp)
                    )
                }, label = {
                    Text(text = tabItem.label, color = white)
                }, modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = if (selectState.value == index) purple else purple200)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//        ) {
//            Tab(
//                selected = selectState.value == 0, onClick = {
//                    selectState.value = 0
//                    scope.launch {
//                        pagerState.animateScrollToPage(0)
//                    }
//                }, modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .background(color = if (selectState.value == 0) purple700 else green200)
//            ) {
//                Text(text = "首页", color = white)
//            }
//            Tab(
//                selected = selectState.value == 1, onClick = {
//                    selectState.value = 1
//                    scope.launch {
//                        pagerState.animateScrollToPage(1)
//                    }
//                }, modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .background(color = if (selectState.value == 1) purple700 else green200)
//            ) {
//                Text(text = "直播", color = white)
//            }
//            Tab(
//                selected = selectState.value == 2, onClick = {
//                    selectState.value = 2
//                    scope.launch {
//                        pagerState.animateScrollToPage(2)
//                    }
//                }, modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .background(color = if (selectState.value == 2) purple700 else green200)
//            ) {
//                Text(text = "智投", color = white)
//            }
//            Tab(
//                selected = selectState.value == 3, onClick = {
//                    selectState.value = 3
//                    scope.launch {
//                        pagerState.animateScrollToPage(3)
//                    }
//                }, modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .background(color = if (selectState.value == 3) purple700 else green200)
//            ) {
//                Text(text = "行情", color = white)
//            }
//            Tab(
//                selected = selectState.value == 4, onClick = {
//                    selectState.value = 4
//                    scope.launch {
//                        pagerState.animateScrollToPage(4)
//                    }
//                }, modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .background(color = if (selectState.value == 4) purple700 else green200)
//            ) {
//                Text(text = "我的", color = white)
//            }
//        }
    }
}

@ExperimentalPagerApi
@HiltViewModel
class NetworkViewModel : ViewModel() {

    val liveData = MutableLiveData<LoadViewState<Any>>()
    fun getData() {
        viewModelScope.launch {
            liveData.value = LoadViewState.Loading
            runCatching {
                delay(2000L)
            }.onSuccess {
                liveData.value = LoadViewState.Success(true)
            }.onFailure {
                liveData.value = LoadViewState.Failure(throwable = it)
            }
        }
    }
}

sealed class LoadViewState<out T> {
    object Loading : LoadViewState<Nothing>()
    data class Success<T>(val data: T) : LoadViewState<T>()
    data class Failure(val errorCode: Int? = -1, val throwable: Throwable) : LoadViewState<Nothing>()
    object Empty : LoadViewState<Nothing>()
}
