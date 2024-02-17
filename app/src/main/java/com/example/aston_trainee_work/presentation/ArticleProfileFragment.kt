package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.databinding.FragmentArticleProfileBinding
import com.example.aston_trainee_work.domain.ArticleItem
import java.text.SimpleDateFormat
import java.util.Locale


class ArticleProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val articleItem = arguments?.getSerializable("article") as ArticleItem

        val binding = FragmentArticleProfileBinding.inflate(inflater, container, false)

        binding.apply {
            collapsingToolbar.title = articleItem.title
            articleTitle.text = articleItem.title
            articleDateTime.text = convertDateTime(articleItem.publishedAt)
            articleSource.text = articleItem.source.name
            if (articleItem.content.isNullOrBlank()) {
                contentNull.visibility = View.VISIBLE
            } else {
                contentNull.visibility = View.GONE
                articleContent.text = articleItem.content
            }

            Glide.with(this@ArticleProfileFragment)
                .load(articleItem.urlToImage)
                .centerCrop()
                .error(R.drawable.no_image_placeholder)
                .into(articleImage)
        }

        return binding.root
    }

    private fun convertDateTime(publishedAt: String): String? {
        val fromFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val formatter = SimpleDateFormat("MMM dd, yyyy | KK:mm a", Locale.ENGLISH)

        val publishDateTime = fromFormatter.parse(publishedAt)
        return publishDateTime?.let { formatter.format(it) }
    }

    companion object {
        fun getNewInstance(articleItem: ArticleItem): ArticleProfileFragment {
            return ArticleProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("article", articleItem)
                }
            }
        }
    }
}