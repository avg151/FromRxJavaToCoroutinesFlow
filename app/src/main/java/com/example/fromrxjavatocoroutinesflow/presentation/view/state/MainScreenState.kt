package com.example.fromrxjavatocoroutinesflow.presentation.view.state

import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel

sealed class MainScreenState {

    data class SuccessMainScreenState(val dataList: List<SuccessDataModel>) : MainScreenState()

    data class ErrorMainScreenState(val errorMessage: String) : MainScreenState()

}
