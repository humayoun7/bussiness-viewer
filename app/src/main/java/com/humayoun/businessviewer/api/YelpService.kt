package com.humayoun.businessviewer.api

import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.model.BusinessSearchResult
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YelpService {
    @GET("v3/businesses/search")
    suspend fun getBusinessSearchResult(
        @Query("location") location: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BusinessSearchResult


    companion object {
        fun create(): YelpService {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor {
                    val request = it.request().newBuilder()
                        .addHeader(Constants.YelpSerivce.AUTHORIZATION_HEADER, Constants.YelpSerivce.AUTHORIZATION_HEADER_VALUE)
                        .build()

                    it.proceed(request)
                })
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.YelpSerivce.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build()

            return retrofit.create(YelpService::class.java)
        }
    }
}