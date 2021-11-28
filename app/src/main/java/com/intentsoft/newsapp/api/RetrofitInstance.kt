package com.intentsoft.newsapp.api

import com.intentsoft.newsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Zokirjon
 * @date 11/24/2021
 */
class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val client = OkHttpClient.Builder().apply {
                addInterceptor(AppInterceptor())
            }.build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api: NewsApi by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}