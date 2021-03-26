package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import com.example.fromrxjavatocoroutinesflow.R
import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.ErrorDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.ErrorMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState.SuccessEmptyMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState.SuccessNotEmptyMainScreenState
import kotlinx.coroutines.channels.ReceiveChannel

abstract class CommonViewModel(
    private val getDataUseCase: GetDataUseCase
) : BaseViewModel<List<SuccessDataModel>, ErrorDataModel,
        SuccessMainScreenState, ErrorMainScreenState>() {

    override val receiveChannel: ReceiveChannel<Result<List<SuccessDataModel>, ErrorDataModel>>
        get() = getDataUseCase.receiveChannel

    companion object {
        internal const val TIMEOUT = 1000L
    }

    override fun successState(result: List<SuccessDataModel>): SuccessMainScreenState {
        return if (result.isNotEmpty()) {
            SuccessNotEmptyMainScreenState(result)
        } else {
            SuccessEmptyMainScreenState(R.string.empty_result)
        }
    }

    override fun errorState(result: ErrorDataModel): ErrorMainScreenState {
        return ErrorMainScreenState(result.toString())
    }
}