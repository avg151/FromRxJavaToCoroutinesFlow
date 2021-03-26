package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.flow.ViewModelFlow
import com.example.fromrxjavatocoroutinesflow.presentation.view.rx.ViewModelRx
import kotlinx.coroutines.FlowPreview

@FlowPreview
class ViewModelFactory(
    private val searchView: SearchView,
    private val getDataUseCase: GetDataUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ViewModelRx::class.java -> ViewModelRx(searchView, getDataUseCase) as T
            ViewModelFlow::class.java -> ViewModelFlow(searchView, getDataUseCase) as T
            else -> throw Exception("Unsupported viewModel class exception")
        }

    }

}