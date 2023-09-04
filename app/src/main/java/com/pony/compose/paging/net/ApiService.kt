package com.pony.compose.paging.net

import com.pony.compose.paging.bean.ExamQuestions
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by pony on 2022/8/17
 *Description->
 */
interface ApiService {
    @GET("jisuapi/driverexamQuery")
    suspend fun getExamList(
        @Query("pagenum") pagenum: Int,
        @Query("pagesize") pagesize: Int = 4,
        @Query("type") type: String = "C1",
        @Query("subject") subject: Int = 1,
        @Query("sort") sort: String = "normal",
        @Query("appkey") appkey: String = "647fd7f08ee823199d919a2a09161345",
    ): ExamQuestions
}