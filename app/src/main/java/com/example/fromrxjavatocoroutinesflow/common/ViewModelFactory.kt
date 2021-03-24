package com.example.fromrxjavatocoroutinesflow.common

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.flow.ViewModelFlow
import com.example.fromrxjavatocoroutinesflow.rx.ViewModelRx

class ViewModelFactory(private val searchView: SearchView) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ViewModelRx::class.java -> ViewModelRx(searchView) as T
            ViewModelFlow::class.java -> ViewModelFlow(searchView) as T
            else -> throw Exception("Unsupport viewModel class exeption")
        }

    }

}