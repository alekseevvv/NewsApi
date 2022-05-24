package com.artava.newsapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel {

    private val _text = MutableLiveData<String>().apply {
        value = "This is favorite Fragment"
    }
    val text: LiveData<String> = _text

}