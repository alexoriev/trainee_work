package com.example.aston_trainee_work.presentation;

import static java.util.stream.Collectors.toList;

import com.example.aston_trainee_work.common.Screens;
import com.example.aston_trainee_work.domain.ArticleItem;
import com.example.aston_trainee_work.domain.Category;
import com.example.aston_trainee_work.domain.GetHeadlinesArticlesListUseCase;
import com.example.aston_trainee_work.domain.SourceWithImage;
import com.example.aston_trainee_work.utils.SourceConverter;
import com.github.terrakok.cicerone.Router;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class HeadlinesTabPresenter extends MvpPresenter<HeadlinesTabView> {
    private final Router router;
    private final GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase;
    private final SourceConverter sourceConverter;
    private final Category category;

    @Inject
    HeadlinesTabPresenter(Router router,
                          GetHeadlinesArticlesListUseCase getHeadlinesArticlesListUseCase,
                          SourceConverter sourceConverter,
                          Category category) {
        this.router = router;
        this.getHeadlinesArticlesListUseCase = getHeadlinesArticlesListUseCase;
        this.sourceConverter = sourceConverter;
        this.category = category;
    }

    public void loadFirstPage() {
        Disposable disposable =
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(category, 1)
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
                getHeadlinesArticlesListUseCase.getHeadlinesArticlesList(category, page)
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

    public void goToArticleProfile(ArticleItem articleItem) {
        router.navigateTo(Screens.INSTANCE.articleProfile(articleItem));
    }
}
