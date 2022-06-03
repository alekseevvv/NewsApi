package com.artava.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.newsapi.MainRepository
import com.artava.newsapi.model.ArticleResponse
import com.artava.newsapi.model.SourceResponce
import com.artava.newsapi.service.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeLineViewModel(val repository: MainRepository) : ViewModel() {
    init {
        getAllMovies()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is timeline Fragment"
    }
    val text: LiveData<String> = _text

    private val _HeadList = MutableLiveData<Result<ArticleResponse?>?>()
    val headList: LiveData<Result<ArticleResponse?>?> = _HeadList

    fun getAllMovies(){
        try{
            viewModelScope.launch(Dispatchers.IO){
                val response = repository.getTopHeadlines()
                response.let {
                    val result = it?.body()
                    result.let { list->
                        _HeadList.postValue(Result.Success(list))
                    }
                }
            }
        } catch (error: Exception){
            _HeadList.postValue(Result.Error(error))
        }
    }
}