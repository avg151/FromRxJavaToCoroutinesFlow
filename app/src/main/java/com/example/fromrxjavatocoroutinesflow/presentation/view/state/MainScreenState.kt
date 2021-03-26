package com.example.fromrxjavatocoroutinesflow.presentation.view.state

import androidx.annotation.StringRes
import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel

sealed class MainScreenState {

    sealed class SuccessMainScreenState : MainScreenState() {

        data class SuccessNotEmptyMainScreenState(val dataList: List<SuccessDataModel>) :
            SuccessMainScreenState()

        data class SuccessEmptyMainScreenState(@StringRes val emptyMessage: Int) :
            SuccessMainScreenState()

    }

    data class ErrorMainScreenState(val errorMessage: String) : MainScreenState()

}
