package com.example.aston_trainee_work.presentation

import com.example.aston_trainee_work.domain.SourceItem

interface OnSourceInteractionListener {
    fun onGetArticlesBySource(source: SourceItem)
}