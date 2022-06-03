package com.artava.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.databinding.ItemArticleBinding
import com.artava.newsapi.databinding.ItemSourceBinding
import com.artava.newsapi.model.ArticleModel
import com.artava.newsapi.model.SourceModel

class TimeLineRecyclerViewAdapter(private val sourceList: List<ArticleModel>) :
    RecyclerView.Adapter<TimeLineRecyclerViewAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvDesc: TextView = binding.tvDescr
       // val tvTitle: TextView = binding.tvTitle
        val tvLang: TextView = binding.tvSource
        val ivImg: ImageView = binding.ivImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemArticleBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = sourceList.get(position)
        holder.tvDesc.text = item.description
      //  holder.tvTitle.text = item.title
        holder.tvLang.text = item.source.name

    }

    override fun getItemCount(): Int {
        return sourceList.size
    }


}