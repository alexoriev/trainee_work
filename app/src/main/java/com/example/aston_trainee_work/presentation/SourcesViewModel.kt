package com.example.aston_trainee_work.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_trainee_work.common.Screens
import com.example.aston_trainee_work.common.Screens.sourceArticles
import com.example.aston_trainee_work.domain.GetSourcesListUseCase
import com.example.aston_trainee_work.domain.SourceItem
import com.example.aston_trainee_work.utils.InternetConnectionChecker
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import java.lang.Exception

class SourcesViewModel(
    private val getSourcesListUseCase: GetSourcesListUseCase,
    private val router: Router
) : ViewModel() {

    val data = MutableLiveData<List<SourceItem>>()

    fun getSources() {
        if (!InternetConnectionChecker.isConnected()) {
            router.navigateTo(Screens.noInternet())
            return
        }
        viewModelScope.launch {
            try {
                val wrapper = getSourcesListUseCase.getSourcesList()
                if (wrapper.isSuccessful) {
                    data.value = wrapper.sources
                } else {
                    router.navigateTo(Screens.error())
                }
            } catch (e: Exception) {
                router.navigateTo(Screens.error())
            }
        }
    }

    fun goToSourceArticles(sourceItem: SourceItem) {
        router.navigateTo(sourceArticles(sourceItem))
    }
}
