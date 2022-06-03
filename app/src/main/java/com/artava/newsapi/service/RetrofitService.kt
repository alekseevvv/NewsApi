package com.artava.newsapi.service

import com.artava.newsapi.model.ArticleResponse
import com.artava.newsapi.model.SourceModel
import com.artava.newsapi.model.SourceResponce
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @GET("/v2/top-headlines/sources")
    suspend fun getSourceList(
        @Query("apiKey")
        apiKey: String = "a7fbdcf3b5504de1b83325d455e277f6"
    ): Response<SourceResponce>

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")
        apiKey: String = "a7fbdcf3b5504de1b83325d455e277f6",
        @Query("country")
        counry: String = "ru"
    ): Response<ArticleResponse>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}