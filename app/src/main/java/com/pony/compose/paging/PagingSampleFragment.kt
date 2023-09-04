package com.pony.compose.paging

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.black333
import com.pony.compose.paging.net.ExamSource
import com.pony.compose.paging.repository.PagingRepository
import com.pony.compose.paging.ui.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *Created by pony on 2022/8/17
 *Description->
 */
@AndroidEntryPoint
class PagingSampleFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        SwipeRefreshList()
    }

    @Composable
    private fun SwipeRefreshList() {
        val viewModel: PagingSampleViewModel = hiltViewModel()
        val lazyPagingExamList = viewModel.examList.collectAsLazyPagingItems()
        val loadState = lazyPagingExamList.loadState
        if (loadState.refresh is LoadState.Error && lazyPagingExamList.itemCount <= 0) {
            //刷新的时候，如果itemCount小于或等于0，第一次加载异常
            ErrorContentComponent {
                lazyPagingExamList.retry()
            }
        } else {
            if (loadState.refresh is LoadState.Loading && lazyPagingExamList.itemCount <= 0) {
                LoadingContentComponent()
            } else {
                val refreshState = rememberSwipeRefreshState(isRefreshing = false)
                val showErrorTip = remember {
                    mutableStateOf(false)
                }
                if (showErrorTip.value) {
                    RefreshErrorTip(showErrorTip)
                }
                SwipeRefresh(state = refreshState, onRefresh = { lazyPagingExamList.refresh() }, modifier = Modifier.fillMaxSize()) {
                    refreshState.isRefreshing = lazyPagingExamList.loadState.refresh is LoadState.Loading
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(lazyPagingExamList) { index, data ->
                            data?.let {
                                QItemView(
                                    index = index,
                                    que = it,
                                    onClick = { Toast.makeText(context, "ccc", Toast.LENGTH_SHORT).show() },
                                )
                            }
                        }
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                //加载更多，底部loading
                                item { LoadingFooter() }
                            }
                            is LoadState.Error -> {
                                //加载更多异常
                                item {
                                    LoadMoreErrorComponent {
                                        lazyPagingExamList.retry()
                                    }
                                }
                            }
                            LoadState.NotLoading(endOfPaginationReached = true) -> {
                                //已经没有更多数据了
                            }
                            else -> {
                                if (loadState.refresh is LoadState.Error) {
                                    showErrorTip.value = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RefreshErrorTip(showErrorTip: MutableState<Boolean>) {
    if (showErrorTip.value) {
        val scope = rememberCoroutineScope()
        AlertDialog(
            onDismissRequest = { showErrorTip.value = false },
            confirmButton = {},
            dismissButton = {

            },
            text = { Text(text = "刷新失败", color = black333, fontSize = 16.sp) },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
        LaunchedEffect(key1 = Unit, block = {
            scope.launch {
                delay(3000)
                showErrorTip.value = false
            }
        })
    }
}


@HiltViewModel
class PagingSampleViewModel : ViewModel() {
    private val config = PagingConfig(pageSize = 8, initialLoadSize = 8, prefetchDistance = 2)
    val examList = Pager(config = config, initialKey = 1) {
        ExamSource(config, PagingRepository)
    }.flow.cachedIn(viewModelScope)
}
