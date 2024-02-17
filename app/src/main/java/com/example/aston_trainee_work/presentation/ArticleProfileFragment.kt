package com.example.aston_trainee_work.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
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
                articleContent.movementMethod = LinkMovementMethod.getInstance()
                contentNull.visibility = View.GONE
                val content = getContent(articleItem)
                articleContent.text = content
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

    private fun getContent(articleItem: ArticleItem): SpannableString {
        if (articleItem.content!!.contains('.')) {
            val startSpan = articleItem.content.indexOfLast { c -> c == '.' } + 1
            val spannable = SpannableString(articleItem.content)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.url))
                    startActivity(browserIntent)
                }
            }
            spannable.setSpan(clickableSpan, startSpan, spannable.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            return spannable
        } else {
            val spannable = SpannableString(articleItem.content)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.url))
                    startActivity(browserIntent)
                }
            }
            spannable.setSpan(clickableSpan, 0, spannable.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            return spannable
        }
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