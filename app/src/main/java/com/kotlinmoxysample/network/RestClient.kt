package com.kotlingithubapi.network

import com.kotlinmoxysample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Valentyn on 08.06.17.
 */

class RestClient {

    private val baseUrl = BuildConfig.API_ENDPOINT

    private var httpClient = OkHttpClient.Builder()

    init {
        initOkHttp()
    }

    private fun initOkHttp() {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun getRetrofit(): Retrofit {
        return builder.client(httpClient.build()).build()
    }

    inline fun <reified T> createService(): T = getRetrofit().create(T::class.java)

    fun <T> createService(serviceClass: Class<T>): T {
        val retrofit = getRetrofit()
        return retrofit.create(serviceClass)
    }

    fun resetRetrofit() {
        httpClient = OkHttpClient.Builder()
        initOkHttp()
    }
}
