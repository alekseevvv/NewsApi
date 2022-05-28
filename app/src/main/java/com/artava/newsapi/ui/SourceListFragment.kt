package com.artava.newsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.MainRepository
import com.artava.newsapi.R
import com.artava.newsapi.adapter.SourceRecyclerViewAdapter
import com.artava.newsapi.model.SourceResponce
import com.artava.newsapi.service.RetrofitService
import com.artava.newsapi.viewmodel.SouceViewModelFactory
import com.artava.newsapi.viewmodel.SourceViewModel

class SourceListFragment : Fragment() {
    lateinit var viewModel: SourceViewModel
    lateinit var adapter: SourceRecyclerViewAdapter
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_source_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SouceViewModelFactory(MainRepository(retrofitService))
        )[SourceViewModel::class.java]
        viewModel.text.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.editTextTextPersonName).text = it
        })
        viewModel.sourceList.observe(viewLifecycleOwner, Observer { response ->
            if (response.status == "ok") {
                adapter = SourceRecyclerViewAdapter(response.sources)
                view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
            }
        })
    }


}