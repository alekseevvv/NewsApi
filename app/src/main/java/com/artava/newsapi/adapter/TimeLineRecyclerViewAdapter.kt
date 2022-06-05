package com.artava.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artava.newsapi.databinding.ItemArticleBinding
import com.artava.newsapi.model.ArticleModel
import com.squareup.picasso.Picasso

class TimeLineRecyclerViewAdapter(private val articleList: List<ArticleModel>) :
    RecyclerView.Adapter<TimeLineRecyclerViewAdapter.RecyclerViewHolder>() {
    var onItemClick: ((ArticleModel) -> Unit)? = null

    inner class RecyclerViewHolder(binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvDesc: TextView = binding.tvDescr
        val tvLang: TextView = binding.tvSource
        val ivImg: ImageView = binding.ivImg
        val tvTime: TextView = binding.tvTime

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(articleList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemArticleBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = articleList[position]
        holder.tvDesc.text = item.description
        holder.tvLang.text = item.source.name
        holder.tvTime.text = item.publishedAt
        Picasso.get().load(item.urlToImage).into(holder.ivImg)

    }

    override fun getItemCount(): Int {
        return articleList.size
    }


}