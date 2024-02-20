package com.example.aston_trainee_work.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

class ArticleProfileViewModel(private val router: Router) : ViewModel() {

    fun onBackPressed() {
        router.exit()
    }

}