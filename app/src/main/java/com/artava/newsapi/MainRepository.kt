package com.artava.newsapi

import com.artava.newsapi.service.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() = retrofitService.getSourceList()

}