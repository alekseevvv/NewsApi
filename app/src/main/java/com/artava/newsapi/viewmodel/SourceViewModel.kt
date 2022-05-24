package com.artava.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SourceViewModel: ViewModel() {

    init {
        println("ViewModel Source created")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is source Fragment"
    }
    val text: LiveData<String> = _text

    override fun onCleared() {
        println("ViewModel Source cleared")

        super.onCleared()
    }
}