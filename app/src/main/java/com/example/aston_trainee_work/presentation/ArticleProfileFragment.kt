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
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.databinding.FragmentArticleProfileBinding
import com.example.aston_trainee_work.domain.ArticleItem

class ArticleProfileFragment : Fragment() {
    private lateinit var viewModel: ArticleProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).hideActionBar()
        val binding = FragmentArticleProfileBinding.inflate(inflater, container, false)
        val articleItem = arguments?.getSerializable("article") as ArticleItem
        viewModel = ArticleProfileViewModel(
            (activity?.application as ArticlesApp).appComponent.getRouter(),
            (activity?.application as ArticlesApp).appComponent.getSaveArticleUseCase(),
            (activity?.application as ArticlesApp).appComponent.getIsSavedArticleUseCase(),
            articleItem
        )

        binding.apply {
            collapsingToolbar.title = articleItem.title
            articleTitle.text = articleItem.title
            articleDateTime.text = viewModel.convertDateTime(articleItem.publishedAt)
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

            backButton.setOnClickListener {
                viewModel.onBackPressed()
            }

            if (!viewModel.isSavedArticle()) {
                saveButton.setImageResource(R.drawable.save)
                saveButton.setOnClickListener {
                    viewModel.saveArticle()
                    saveButton.setImageResource(R.drawable.saved)
                }
            } else {
                saveButton.setImageResource(R.drawable.saved)
            }
        }

        return binding.root
    }

    private fun getContent(articleItem: ArticleItem): SpannableString {
        if (articleItem.content!!.contains('.') || articleItem.content.contains('…')) {
            val startSpan = articleItem.content.indexOfLast { c -> c == '.' || c == '…' } + 2
            val spannable = SpannableString(articleItem.content)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.url))
                    startActivity(browserIntent)
                }
            }
            spannable.setSpan(
                clickableSpan,
                startSpan,
                spannable.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            return spannable
        } else {
            val spannable = SpannableString(articleItem.content)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.url))
                    startActivity(browserIntent)
                }
            }
            spannable.setSpan(
                clickableSpan,
                0,
                spannable.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
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