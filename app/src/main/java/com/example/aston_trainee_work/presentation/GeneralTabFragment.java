package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aston_trainee_work.common.ArticlesApp;
import com.example.aston_trainee_work.databinding.FragmentGeneralTabBinding;
import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;
import com.example.aston_trainee_work.utils.SourceConverter;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class GeneralTabFragment extends MvpAppCompatFragment implements GeneralTabView {
    private static final int PAGE_START = 1;
    private static final int TOTAL_PAGES = 5;

    private FragmentGeneralTabBinding binding;
    private ArticlesAdapter adapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;

    @InjectPresenter
    GeneralTabPresenter generalTabPresenter;

    @ProvidePresenter
    GeneralTabPresenter providePresenter() {
        GetHeadlinesArticlesListUseCase useCase =
                ((ArticlesApp) getActivity().getApplication()).getAppComponent().getGetHeadlinesArticlesListUseCase();
        SourceConverter sourceConverter =
                ((ArticlesApp) getActivity().getApplication()).getAppComponent().getSourceConverter();
        return new GeneralTabPresenter(useCase, sourceConverter);
    }

    public GeneralTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneralTabBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false);
        adapter = new ArticlesAdapter();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                binding.articlesRv.getContext(),
                DividerItemDecoration.VERTICAL);
        binding.articlesRv.addItemDecoration(dividerItemDecoration);
        binding.articlesRv.setLayoutManager(linearLayoutManager);
        binding.articlesRv.setAdapter(adapter);

        addOnScrollListener(binding.articlesRv, linearLayoutManager);
        generalTabPresenter.loadFirstPage();

        return binding.getRoot();
    }

    @Override
    public void showArticles(List<ArticleItem> articles) {
        adapter.setList(articles);
    }

    @Override
    public void onFirstPageLoaded(List<ArticleItem> articles) {
        adapter.setList(articles);

        if (currentPage <= TOTAL_PAGES) {
            adapter.showLoading();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void onNextPageLoaded(List<ArticleItem> articles) {
        adapter.hideLoading();
        isLoading = false;
        adapter.addAll(articles);
        if (currentPage != TOTAL_PAGES) {
            adapter.showLoading();
        } else {
            isLastPage = true;
        }
    }

    private void addOnScrollListener(RecyclerView recyclerView,
                                     LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                generalTabPresenter.loadNextPage(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }
}