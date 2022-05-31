package com.artava.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artava.newsapi.MainRepository
import com.artava.newsapi.model.SourceResponce
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

    private val _sourceList = MutableLiveData<SourceResponce>()
    val sourceList: LiveData<SourceResponce> = _sourceList

     fun getAllMovies() = viewModelScope.launch{
        val response = repository.getAllMovies()
        _sourceList.postValue(response)
    }
}