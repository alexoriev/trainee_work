package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.databinding.FragmentArticlesListBinding
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.SourceItem

class SourceArticlesFragment : Fragment() {
    private lateinit var viewModel: SourceArticlesViewModel
    private lateinit var binding: FragmentArticlesListBinding
    private lateinit var adapter: ArticlesAdapter

    private val PAGE_START = 1
    private val TOTAL_PAGES = 5
    private var isNextPageLoading = false
    private var isCurrentPageLast = false
    private var currentPage = PAGE_START

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val source = arguments?.getSerializable("source") as SourceItem
        (activity as MainActivity).apply {
            setActionBarTitle(source.name)
            showActionBar()
        }.showActionBar()

        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        viewModel = SourceArticlesViewModel(
            (activity?.application as ArticlesApp).appComponent.getGetSourceArticlesListUseCase(),
            (activity?.application as ArticlesApp).appComponent.getRouter(),
            source
        )

        viewModel.loadFirstPage(PAGE_START)

        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ArticlesAdapter { articleItem: ArticleItem ->
            viewModel.goToArticleProfile(articleItem)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if (currentPage == PAGE_START) {
                onFirstPageLoaded(it)
            } else {
                onNextPageLoaded(it)
            }
        }

        val dividerItemDecoration = DividerItemDecoration(
            binding.articlesRv.context,
            DividerItemDecoration.VERTICAL
        )
        binding.articlesRv.addItemDecoration(dividerItemDecoration)
        binding.articlesRv.layoutManager = linearLayoutManager
        binding.articlesRv.adapter = adapter
        addOnScrollListener(binding.articlesRv, linearLayoutManager)

        return binding.root
    }

    private fun onFirstPageLoaded(articles: List<ArticleItem>) {
        adapter.setList(articles)
        if (currentPage <= TOTAL_PAGES) {
            adapter.showLoading()
        } else {
            isCurrentPageLast = true
        }
    }

    private fun onNextPageLoaded(articles: List<ArticleItem>) {
        adapter.hideLoading()
        isNextPageLoading = false
        adapter.addAll(articles)
        if (currentPage != TOTAL_PAGES) {
            adapter.showLoading()
        } else {
            isCurrentPageLast = true
        }
    }

    private fun addOnScrollListener(
        recyclerView: RecyclerView,
        linearLayoutManager: LinearLayoutManager
    ) {
        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isNextPageLoading = true
                currentPage += 1
                viewModel.loadNextPage(currentPage)
            }

            override fun isLastPage(): Boolean {
                return isCurrentPageLast
            }

            override fun isLoading(): Boolean {
                return isNextPageLoading
            }
        })
    }

    companion object {
        fun getNewInstance(sourceItem: SourceItem): SourceArticlesFragment {
            return SourceArticlesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("source", sourceItem)
                }
            }
        }
    }
}