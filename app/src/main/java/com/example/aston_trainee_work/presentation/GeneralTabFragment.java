package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;

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
    private FragmentGeneralTabBinding binding;
    private ArticlesAdapter adapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneralTabBinding.inflate(inflater, container, false);
        adapter = new ArticlesAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                binding.articlesRv.getContext(),
                DividerItemDecoration.VERTICAL);
        binding.articlesRv.addItemDecoration(dividerItemDecoration);

        binding.articlesRv.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void showArticles(List<ArticleItem> articles) {
        adapter.setData(articles);
    }
}