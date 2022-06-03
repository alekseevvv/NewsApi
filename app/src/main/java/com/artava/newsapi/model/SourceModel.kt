package com.artava.newsapi.model

data class SourceModel(
   val id:String,
   val name: String,
   val description: String,
   val url: String,
   val category: String,
   val language: String,
   val country: String
)

data class SourceResponce (
   val status: String,
   val sources: List<SourceModel>
)