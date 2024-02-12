package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.aston_trainee_work.databinding.FragmentGeneralTabBinding;
import com.example.aston_trainee_work.di.DaggerAppComponent;
import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;

import java.util.List;

import javax.inject.Inject;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class GeneralTabFragment extends MvpAppCompatFragment implements GeneralTabView {

    private FragmentGeneralTabBinding binding;
    private ArticlesAdapter adapter;

    @Inject
    GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase =
            DaggerAppComponent.create().getGetHeadlinesArticlesListUseCase();

    @InjectPresenter
    GeneralTabPresenter generalTabPresenter;

    @ProvidePresenter
    GeneralTabPresenter providePresenter() {
        return new GeneralTabPresenter(getHeadlinesArticlesListUseCase);
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
        binding.articlesRv.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void showArticles(List<ArticleItem> articles) {
        adapter.setData(articles);
    }
}