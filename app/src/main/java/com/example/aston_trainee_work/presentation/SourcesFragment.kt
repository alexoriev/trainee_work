package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.common.Screens.noInternet
import com.example.aston_trainee_work.databinding.FragmentSourcesBinding
import com.example.aston_trainee_work.domain.SourceItem

class SourcesFragment : Fragment() {
    private lateinit var viewModel: SourcesViewModel
    private lateinit var binding: FragmentSourcesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).apply {
            setActionBarTitle("Sources")
            showActionBar()
        }.showActionBar()

        val getSourcesListUseCase = ArticlesApp.INSTANCE.appComponent.getGetSourcesListUseCase()
        val router = ArticlesApp.INSTANCE.appComponent.getRouter()

        if (!(activity as MainActivity?)!!.isConnected()) {
            router.navigateTo(noInternet())
            return null
        } else {

            viewModel = SourcesViewModel(getSourcesListUseCase, router)
            viewModel.getSources()

            binding = FragmentSourcesBinding.inflate(inflater, container, false)

            val adapter = SourcesAdapter(object : OnSourceInteractionListener {
                override fun onGetArticlesBySource(source: SourceItem) {
                    viewModel.goToSourceArticles(source)
                }
            })

            viewModel.data.observe(viewLifecycleOwner) { sources ->
                adapter.submitList(sources)
            }

            binding.apply {
                val dividerItemDecoration =
                    DividerItemDecoration(sourcesRv.context, DividerItemDecoration.VERTICAL)
                sourcesRv.addItemDecoration(dividerItemDecoration)
                sourcesRv.adapter = adapter
            }

            return binding.root
        }
    }
}
