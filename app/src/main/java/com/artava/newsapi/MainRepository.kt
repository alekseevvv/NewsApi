package com.artava.newsapi

import com.artava.newsapi.model.ArticleResponse
import com.artava.newsapi.model.SourceResponce
import com.artava.newsapi.service.RetrofitService
import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllSource(): Response<SourceResponce>? {
        try {
            val responce = retrofitService.getSourceList()
            responce.let {
                return it
            }
        } catch (error: Exception){
            return null
        }
    }

    suspend fun getTopHeadlines(): Response<ArticleResponse>? {
        try {
            val responce = retrofitService.getTopHeadlines()
            responce.let {
                return it
            }
        } catch (error: Exception){
            return null
        }
    }
}