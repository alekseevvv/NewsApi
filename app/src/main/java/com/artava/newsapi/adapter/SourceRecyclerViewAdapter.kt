package com.artava.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.databinding.ItemSourceBinding
import com.artava.newsapi.model.SourceModel

class SourceRecyclerViewAdapter(private val sourceList: List<SourceModel>) :
    RecyclerView.Adapter<SourceRecyclerViewAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(binding: ItemSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvDesc: TextView = binding.tvDescr
        val tvTitle: TextView = binding.tvTitle
        val tvLang: TextView = binding.tvLang

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemSourceBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = sourceList[position]
        holder.tvDesc.text = item.description
        holder.tvTitle.text = item.name
        holder.tvLang.text = item.country
    }

    override fun getItemCount(): Int {
        return sourceList.size
    }


}