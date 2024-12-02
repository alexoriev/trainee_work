package com.example.aston_trainee_work.common

import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.SourceItem
import com.example.aston_trainee_work.presentation.ArticleProfileFragment
import com.example.aston_trainee_work.presentation.ErrorFragment
import com.example.aston_trainee_work.presentation.FiltersFragment
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.NoInternetFragment
import com.example.aston_trainee_work.presentation.SavedFragment
import com.example.aston_trainee_work.presentation.SearchArticlesFragment
import com.example.aston_trainee_work.presentation.SourceArticlesFragment
import com.example.aston_trainee_work.presentation.SourcesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun headlines() = FragmentScreen { HeadlinesFragment() }
    fun saved() = FragmentScreen { SavedFragment() }
    fun sources() = FragmentScreen { SourcesFragment() }
    fun noInternet() = FragmentScreen { NoInternetFragment() }
    fun error() = FragmentScreen { ErrorFragment() }
    fun filters() = FragmentScreen { FiltersFragment() }

    fun articleProfile(articleItem: ArticleItem) = FragmentScreen {
        ArticleProfileFragment.getNewInstance(articleItem)
    }

    fun sourceArticles(sourceItem: SourceItem) = FragmentScreen {
        SourceArticlesFragment.getNewInstance(sourceItem)
    }

    fun searchArticles(query: String?) = FragmentScreen {
        SearchArticlesFragment.getNewInstance(query)
    }
}