package com.artava.newsapi.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artava.newsapi.MainRepository
import com.artava.newsapi.viewmodel.TimeLineViewModel

class TimeLineViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TimeLineViewModel::class.java)) {
            TimeLineViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}