package com.example.aston_trainee_work.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_trainee_work.common.Screens.sourceArticles
import com.example.aston_trainee_work.domain.GetSourcesListUseCase
import com.example.aston_trainee_work.domain.SourceItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val getSourcesListUseCase: GetSourcesListUseCase,
    private val router: Router
) : ViewModel() {

    val data = MutableLiveData<List<SourceItem>>()

    fun getSources() {
        viewModelScope.launch {
            data.value = getSourcesListUseCase.getSourcesList()
        }
    }

    fun goToSourceArticles(sourceItem: SourceItem) {
        router.navigateTo(sourceArticles(sourceItem))
    }
}
