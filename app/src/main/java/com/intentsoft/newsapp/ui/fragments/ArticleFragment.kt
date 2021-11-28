package com.intentsoft.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.intentsoft.newsapp.R
import com.intentsoft.newsapp.databinding.FragmentArticleBinding
import com.intentsoft.newsapp.ui.NewsActivity
import com.intentsoft.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsActivity = activity as NewsActivity
        viewModel = newsActivity.viewModel

        val article = args.article

        binding.tvTitle.text = article.title
        binding.tvDescription.text = article.description
        binding.tvPublishedAt.text = article.publishedAt

        Glide.with(binding.root).load(article.urlToImage).into(binding.ivArticleImage)

        binding.btnSave.setOnClickListener {
            viewModel.saveNews(article)
            binding.btnSave.setImageResource(R.drawable.ic_favourite_selected)
            Toast.makeText(requireContext(), getString(R.string.successfull_saved), Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            newsActivity.onBackPressed()
        }
    }
}