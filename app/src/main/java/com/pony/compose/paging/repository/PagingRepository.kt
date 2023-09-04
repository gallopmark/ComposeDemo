package com.pony.compose.paging.repository

import com.pony.compose.paging.net.ApiService
import com.pony.compose.paging.net.RetrofitClient

/**
 *Created by pony on 2022/8/17
 *Description->
 */
object PagingRepository {
    suspend fun getExamList(page: Int, pageSize: Int) =
        RetrofitClient.getInstance().create(ApiService::class.java).getExamList(pagenum = page, pagesize = pageSize)
}