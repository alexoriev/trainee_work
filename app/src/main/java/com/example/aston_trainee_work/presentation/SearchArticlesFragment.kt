package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.databinding.FragmentSearchArticlesBinding
import com.example.aston_trainee_work.domain.ArticleItem

class SearchArticlesFragment : Fragment() {
    private lateinit var binding: FragmentSearchArticlesBinding
    private lateinit var adapter: ArticlesSearchAdapter
    private lateinit var viewModel: SearchArticlesViewModel

    private val PAGE_START = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchArticlesBinding.inflate(inflater, container, false)

        val searchArticlesUseCase = ArticlesApp.INSTANCE.appComponent.getSearchArticlesUseCase()
        val searchSavedArticlesUseCase =
            ArticlesApp.INSTANCE.appComponent.getSearchSavedArticlesUseCase()
        val router = ArticlesApp.INSTANCE.appComponent.getRouter()
        viewModel =
            SearchArticlesViewModel(searchArticlesUseCase, searchSavedArticlesUseCase, router)
        val query = arguments?.getString("query")
        if (query != null) {
            viewModel.loadFirstPage(query, PAGE_START)
        }

        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ArticlesSearchAdapter { articleItem: ArticleItem ->
            viewModel.goToArticleProfile(articleItem)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        val dividerItemDecoration = DividerItemDecoration(
            binding.articlesSearchRv.context,
            DividerItemDecoration.VERTICAL
        )
        binding.articlesSearchRv.addItemDecoration(dividerItemDecoration)
        binding.articlesSearchRv.layoutManager = linearLayoutManager
        binding.articlesSearchRv.adapter = adapter

        return binding.root
    }

    companion object {
        fun getNewInstance(query: String?): SearchArticlesFragment {
            return SearchArticlesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("query", query)
                }
            }
        }
    }
}