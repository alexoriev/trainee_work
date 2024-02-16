package com.example.aston_trainee_work.common

import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.presentation.ArticleProfileFragment
import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun headlines() = FragmentScreen { HeadlinesFragment() }
    fun articleProfile(articleItem: ArticleItem) = FragmentScreen {
        ArticleProfileFragment.getNewInstance(articleItem)
    }
}