package com.amazing.eye.network

import com.amazing.eye.BuildConfig
import com.amazing.eye.network.interceptors.Level
import com.amazing.eye.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestCreator {

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(false)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(
            LoggingInterceptor.Builder()//构建者模式
                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
                .build()
        )
        .build()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(RestService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    companion object {
        @Volatile
        private var instance: RestCreator? = null

        fun getInstance(): RestCreator {
            if (instance == null) {
                synchronized(RestCreator::class) {
                    if (instance == null) {
                        instance = RestCreator()
                    }
                }
            }
            return instance!!
        }
    }

    fun getRestService(): RestService {
        return retrofit.create(RestService::class.java)
    }

}