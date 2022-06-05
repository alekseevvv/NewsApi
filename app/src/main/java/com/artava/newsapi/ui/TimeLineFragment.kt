package com.artava.newsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artava.newsapi.MainRepository
import com.artava.newsapi.R
import com.artava.newsapi.adapter.TimeLineRecyclerViewAdapter
import com.artava.newsapi.databinding.FragmentTimeLineBinding
import com.artava.newsapi.service.Result
import com.artava.newsapi.service.RetrofitService
import com.artava.newsapi.viewmodel.TimeLineViewModel
import com.artava.newsapi.viewmodel.factory.TimeLineViewModelFactory

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
        binding = FragmentTimeLineBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            TimeLineViewModelFactory(MainRepository(retrofitService))
        )[TimeLineViewModel::class.java]

        viewModel.headList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    if (result.data?.articles != null) {
                        adapter = TimeLineRecyclerViewAdapter(result.data.articles)
                        binding.recyclerView.adapter = adapter
                        println(result.data.articles[3].description)
                        adapter.onItemClick = { article ->

                            val transaction = fragmentManager?.beginTransaction()
                            transaction?.replace(
                                R.id.container,
                                WebViewFragment.newInstance(article.url)
                            )
                            transaction?.addToBackStack(null)
                            transaction?.commit()
                        }
                    }
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