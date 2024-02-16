package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.databinding.FragmentArticleProfileBinding
import com.example.aston_trainee_work.domain.ArticleItem


class ArticleProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val articleItem = arguments?.getSerializable("article") as ArticleItem

        val binding = FragmentArticleProfileBinding.inflate(inflater, container, false)

        binding.apply {
            articleTitle.text = articleItem.title
            articleDateTime.text = articleItem.publishedAt
            articleSource.text = articleItem.source.name
            articleContent.text = articleItem.content

            Glide.with(this@ArticleProfileFragment)
                .load(articleItem.urlToImage)
                .centerCrop()
                .error(R.drawable.headlines_placeholder)
                .into(articleImage)
        }

        return binding.root
    }

    companion object {
        fun getNewInstance(articleItem: ArticleItem) : ArticleProfileFragment {
            return ArticleProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("article", articleItem)
                }
            }
        }
    }
}