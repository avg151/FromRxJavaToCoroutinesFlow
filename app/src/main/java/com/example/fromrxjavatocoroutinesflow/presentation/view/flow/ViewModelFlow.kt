package com.example.fromrxjavatocoroutinesflow.presentation.view.flow

import androidx.appcompat.widget.SearchView
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonViewModel

class ViewModelFlow(
    searchView: SearchView,
    getDataUseCase: GetDataUseCase
) : CommonViewModel(getDataUseCase)
