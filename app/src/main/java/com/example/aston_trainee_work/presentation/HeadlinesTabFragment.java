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
import com.example.aston_trainee_work.common.Screens;
import com.example.aston_trainee_work.databinding.FragmentHeadlinesTabBinding;
import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.Category;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;
import com.example.aston_trainee_work.utils.SourceConverter;
import com.github.terrakok.cicerone.Router;

import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class HeadlinesTabFragment extends MvpAppCompatFragment implements HeadlinesTabView {
    private static final int PAGE_START = 1;
    private static final int TOTAL_PAGES = 5;

    private final Category category;
    private FragmentHeadlinesTabBinding binding;
    private ArticlesAdapter adapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;

    @InjectPresenter
    HeadlinesTabPresenter headlinesTabPresenter;

    @ProvidePresenter
    HeadlinesTabPresenter providePresenter() {
        GetHeadlinesArticlesListUseCase useCase =
                ((ArticlesApp) getActivity().getApplication()).getAppComponent().getGetHeadlinesArticlesListUseCase();
        SourceConverter sourceConverter =
                ((ArticlesApp) getActivity().getApplication()).getAppComponent().getSourceConverter();
        return new HeadlinesTabPresenter(useCase, sourceConverter, category);
    }

    public HeadlinesTabFragment(Category category) {
        this.category = category;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHeadlinesTabBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false);
        adapter = new ArticlesAdapter(articleItem -> {
            Router router =
                    ((ArticlesApp) getActivity().getApplication()).getAppComponent().getRouter();
            router.navigateTo(Screens.INSTANCE.articleProfile(articleItem));
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                binding.articlesRv.getContext(),
                DividerItemDecoration.VERTICAL);
        binding.articlesRv.addItemDecoration(dividerItemDecoration);
        binding.articlesRv.setLayoutManager(linearLayoutManager);
        binding.articlesRv.setAdapter(adapter);

        addOnScrollListener(binding.articlesRv, linearLayoutManager);
        headlinesTabPresenter.loadFirstPage();

        return binding.getRoot();
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
                headlinesTabPresenter.loadNextPage(currentPage);
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