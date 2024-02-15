package com.example.aston_trainee_work.presentation;

import static java.util.stream.Collectors.toList;

import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.Category;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;
import com.example.aston_trainee_work.domain.SourceWithImage;
import com.example.aston_trainee_work.utils.SourceConverter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class GeneralTabPresenter extends MvpPresenter<GeneralTabView> {
    GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase;
    SourceConverter sourceConverter;

    @Inject
    GeneralTabPresenter(GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase,
                        SourceConverter sourceConverter) {
        this.getHeadlinesArticlesListUseCase = getHeadlinesArticlesListUseCase;
        this.sourceConverter = sourceConverter;
    }

    @Override
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        Disposable disposable =
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(Category.GENERAL, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(headlinesResponse -> {
                            List<ArticleItem> articles =
                                    headlinesResponse.getArticles().stream()
                                            .map(article -> {
                                                SourceWithImage sourceWithImage =
                                                        sourceConverter.convert(
                                                                article.getSource());
                                                return article.fromDto(sourceWithImage);
                                            })
                                            .collect(toList());
                            getViewState().showArticles(articles);
                        });
    }

    public void loadFirstPage() {
        Disposable disposable =
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(Category.GENERAL, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(headlinesResponse -> {
                            List<ArticleItem> articles =
                                    headlinesResponse.getArticles().stream()
                                            .map(article -> {
                                                SourceWithImage sourceWithImage =
                                                        sourceConverter.convert(
                                                                article.getSource());
                                                return article.fromDto(sourceWithImage);
                                            })
                                            .collect(toList());
                            getViewState().onFirstPageLoaded(articles);
                        });
    }

    public void loadNextPage(Integer page) {
        Disposable disposable =
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(Category.GENERAL, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(headlinesResponse -> {
                            List<ArticleItem> articles =
                                    headlinesResponse.getArticles().stream()
                                            .map(article -> {
                                                SourceWithImage sourceWithImage =
                                                        sourceConverter.convert(
                                                                article.getSource());
                                                return article.fromDto(sourceWithImage);
                                            })
                                            .collect(toList());
                            getViewState().onNextPageLoaded(articles);
                        });
    }
}
