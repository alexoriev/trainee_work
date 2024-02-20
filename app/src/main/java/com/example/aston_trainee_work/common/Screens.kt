package com.example.aston_trainee_work.common

import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.Category
import com.example.aston_trainee_work.domain.SourceItem
import com.example.aston_trainee_work.presentation.ArticleProfileFragment
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.HeadlinesTabFragment
import com.example.aston_trainee_work.presentation.SavedFragment
import com.example.aston_trainee_work.presentation.SourceArticlesFragment
import com.example.aston_trainee_work.presentation.SourcesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun headlines() = FragmentScreen { HeadlinesFragment() }
    fun saved() = FragmentScreen { SavedFragment() }
    fun sources() = FragmentScreen { SourcesFragment() }

    fun headlinesGeneralTab() = FragmentScreen { HeadlinesTabFragment(Category.GENERAL) }
    fun headlinesBusinessTab() = FragmentScreen { HeadlinesTabFragment(Category.BUSINESS) }
    fun headlinesTechnologyTab() = FragmentScreen { HeadlinesTabFragment(Category.TECHNOLOGY) }

    fun articleProfile(articleItem: ArticleItem) = FragmentScreen {
        ArticleProfileFragment.getNewInstance(articleItem)
    }

    fun sourceArticles(sourceItem: SourceItem) = FragmentScreen {
        SourceArticlesFragment.getNewInstance(sourceItem)
    }
}