package com.pony.compose.paging.net

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pony.compose.paging.bean.Question
import com.pony.compose.paging.repository.PagingRepository
import kotlinx.coroutines.delay

/**
 *Created by pony on 2022/8/17
 *Description->
 */
class ExamSource(private val config: PagingConfig, private val repository: PagingRepository) : PagingSource<Int, Question>() {

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize
            val responseList = repository.getExamList(currentPage, pageSize = pageSize)
                .result?.resultData?.questionList ?: emptyList()
            // 加载分页
            val everyPageSize = config.pageSize
            val initPageSize = config.initialLoadSize
            val preKey = if (currentPage == 1) null else currentPage.minus(1)
            var nextKey: Int? = if (currentPage == 1) {
                (initPageSize / everyPageSize).plus(1)
            } else {
                currentPage.plus(1)
            }
            if (responseList.isEmpty()) {
                nextKey = null
            }
            delay(4000)
            LoadResult.Page(
                data = responseList,
                prevKey = preKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}