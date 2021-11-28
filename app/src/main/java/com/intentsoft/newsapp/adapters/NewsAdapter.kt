package com.intentsoft.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intentsoft.newsapp.R
import com.intentsoft.newsapp.databinding.NewsItemBinding
import com.intentsoft.newsapp.models.Article
import com.intentsoft.newsapp.models.NewsResponse

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]

        holder.binding.apply {
            Glide.with(this.root).load(news.urlToImage).into(ivArticleImage)
            tvTitle.text = news.title
            tvPublishedAt.text = news.publishedAt

        }

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(news) }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null


    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}