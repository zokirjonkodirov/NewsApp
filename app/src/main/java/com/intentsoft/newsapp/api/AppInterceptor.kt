package com.intentsoft.newsapp.api

import com.intentsoft.newsapp.util.Constants
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author Zokirjon
 * @date 11/24/2021
 */
class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", Constants.API_KEY)
            .build()
        return chain.proceed(original)
    }
}