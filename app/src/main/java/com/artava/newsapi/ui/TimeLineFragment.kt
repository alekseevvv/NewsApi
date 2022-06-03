package com.artava.newsapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.MainRepository
import com.artava.newsapi.R
import com.artava.newsapi.adapter.SourceRecyclerViewAdapter
import com.artava.newsapi.adapter.TimeLineRecyclerViewAdapter
import com.artava.newsapi.databinding.FragmentSourceListBinding
import com.artava.newsapi.databinding.FragmentTimeLineBinding
import com.artava.newsapi.service.Result
import com.artava.newsapi.service.RetrofitService
import com.artava.newsapi.viewmodel.SouceViewModelFactory
import com.artava.newsapi.viewmodel.SourceViewModel
import com.artava.newsapi.viewmodel.TimeLineViewModel
import com.artava.newsapi.viewmodel.TimeLineViewModelFactory

class TimeLineFragment : Fragment() {
    lateinit var viewModel: TimeLineViewModel
    lateinit var adapter: TimeLineRecyclerViewAdapter
    lateinit var binding: FragmentTimeLineBinding
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_line, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            TimeLineViewModelFactory(MainRepository(retrofitService))
        )[TimeLineViewModel::class.java]

           viewModel.headList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
               is Result.Success -> {
                    if (result.data?.articles != null) {
                        adapter = TimeLineRecyclerViewAdapter(result.data.articles)
                    }
                    view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
                }
                is Result.Loading -> {
                    println(result)
                }
                is Result.Error -> {
                    println(result.exception)
                }
                else -> {}
            }
        })

    }

}