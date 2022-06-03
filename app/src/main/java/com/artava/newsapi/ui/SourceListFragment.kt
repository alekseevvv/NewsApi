package com.artava.newsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.MainRepository
import com.artava.newsapi.R
import com.artava.newsapi.adapter.SourceRecyclerViewAdapter
import com.artava.newsapi.databinding.FragmentSourceListBinding
import com.artava.newsapi.service.Result
import com.artava.newsapi.service.RetrofitService
import com.artava.newsapi.viewmodel.SouceViewModelFactory
import com.artava.newsapi.viewmodel.SourceViewModel

class SourceListFragment : Fragment() {
    lateinit var viewModel: SourceViewModel
    lateinit var adapter: SourceRecyclerViewAdapter
    lateinit var binding: FragmentSourceListBinding
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSourceListBinding.inflate(inflater, container, false)

        return binding.root
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

        viewModel.sourceList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    if (result.data?.sources != null) {
                        adapter = SourceRecyclerViewAdapter(result.data.sources)
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