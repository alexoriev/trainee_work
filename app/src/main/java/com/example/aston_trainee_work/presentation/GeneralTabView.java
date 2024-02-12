package com.example.aston_trainee_work.presentation;

import com.example.aston_trainee_work.domain.ArticleItem;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface GeneralTabView extends MvpView {
    void showArticles(List<ArticleItem> articles);
}
