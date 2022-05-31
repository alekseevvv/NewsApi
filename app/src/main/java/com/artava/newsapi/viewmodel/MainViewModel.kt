package com.artava.newsapi.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artava.newsapi.ui.TimeLineFragment


class MainViewModel() : ViewModel() {
    private val _fragment = MutableLiveData<Fragment>().apply {
        value = TimeLineFragment()
    }

    val fragment: LiveData<Fragment>
        get() = _fragment
    fun changeFragment(fragment: Fragment) = _fragment.postValue(fragment)
}