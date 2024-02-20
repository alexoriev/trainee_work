package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.databinding.FragmentArticlesListBinding
import com.example.aston_trainee_work.domain.ArticleItem

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentArticlesListBinding
    private lateinit var viewModel: SavedViewModel
    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).apply {
            setActionBarTitle("Saved")
            showActionBar()
        }.showActionBar()

        binding = FragmentArticlesListBinding.inflate(inflater, container, false)

        viewModel = SavedViewModel(
            (activity?.application as ArticlesApp).appComponent.getGetSavedArticlesListUseCase(),
            (activity?.application as ArticlesApp).appComponent.getRouter()
        )

        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        adapter = ArticlesAdapter { articleItem: ArticleItem ->
            viewModel.goToArticleProfile(articleItem)
        }

        val dividerItemDecoration = DividerItemDecoration(
            binding.articlesRv.context,
            DividerItemDecoration.VERTICAL
        )
        binding.articlesRv.addItemDecoration(dividerItemDecoration)
        binding.articlesRv.layoutManager = linearLayoutManager
        binding.articlesRv.adapter = adapter

        adapter.setList(viewModel.getSavedArticlesList())

        return binding.root
    }
}