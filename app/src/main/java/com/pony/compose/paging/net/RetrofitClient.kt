package com.pony.compose.paging.net

import com.pony.compose.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *Created by pony on 2022/8/17
 *Description->
 */
class RetrofitClient private constructor() {

    companion object {
        private const val BASE_URL = "https://way.jd.com"
        private const val DEFAULT_TIMEOUT = 60 //默认超时时间为60s

        private val mInstance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }

        fun getInstance() = mInstance
    }

    private var retrofit: Retrofit

    init {
        val okBuilder = OkHttpClient().newBuilder()
            .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            //调试模式下开启打印信息
            //开启Log,打印网络请求信息
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpClient = okBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}