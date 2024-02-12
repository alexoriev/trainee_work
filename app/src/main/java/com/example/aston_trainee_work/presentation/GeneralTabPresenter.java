package com.example.aston_trainee_work.presentation;

import static java.util.stream.Collectors.toList;

import com.example.aston_trainee_work.data.dto.Article;
import com.example.aston_trainee_work.data.dto.HeadlinesResponse;
import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.Category;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class GeneralTabPresenter extends MvpPresenter<GeneralTabView> {
    GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase;

    @Inject
    GeneralTabPresenter(GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase) {
        this.getHeadlinesArticlesListUseCase = getHeadlinesArticlesListUseCase;
    }

    @Override
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        Disposable disposable =
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(Category.GENERAL)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<HeadlinesResponse>() {
                            @Override
                            public void accept(HeadlinesResponse headlinesResponse) {
                                List<ArticleItem> articles =
                                        headlinesResponse.getArticles().stream()
                                                .map(Article::fromDto)
                                                .collect(toList());
                                getViewState().showArticles(articles);
                            }
                        });
    }
}
