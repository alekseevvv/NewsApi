package com.artava.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.newsapi.MainRepository
import com.artava.newsapi.model.SourceResponce
import com.artava.newsapi.service.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SourceViewModel (private val repository: MainRepository): ViewModel() {

    init {
        println("ViewModel Source created")
        getAllMovies()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is source Fragment"
    }
    val text: LiveData<String> = _text

    private val _sourceList = MutableLiveData<Result<SourceResponce?>?>()
    val sourceList: LiveData<Result<SourceResponce?>?> = _sourceList

    fun getAllMovies(){
        try{
            viewModelScope.launch(Dispatchers.IO){
                val response = repository.getAllSource()
                response.let {
                    val result = it?.body()
                     result.let { list->
                         _sourceList.postValue(Result.Success(list))
                     }
                }
            }
        } catch (error: Exception){
            _sourceList.postValue(Result.Error(error))
        }
    }
}

