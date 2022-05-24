package com.artava.newsapi.viewmodel

import androidx.lifecycle.ViewModel

class SourceViewModel: ViewModel() {

    init {
        println("ViewModel Source created")
    }

    override fun onCleared() {
        println("ViewModel Source cleared")

        super.onCleared()
    }
}