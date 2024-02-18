package com.example.aston_trainee_work.common

import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.presentation.ArticleProfileFragment
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.example.aston_trainee_work.presentation.SavedFragment
import com.example.aston_trainee_work.presentation.SourcesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun headlines() = FragmentScreen { HeadlinesFragment() }
    fun saved() = FragmentScreen { SavedFragment() }
    fun sources() = FragmentScreen { SourcesFragment() }

    fun articleProfile(articleItem: ArticleItem) = FragmentScreen {
        ArticleProfileFragment.getNewInstance(articleItem)
    }
}