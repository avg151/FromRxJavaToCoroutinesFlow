package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.ErrorDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.ErrorMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState
import kotlinx.coroutines.channels.ReceiveChannel


abstract class CommonViewModel(
    private val getDataUseCase: GetDataUseCase
) : BaseViewModel<List<SuccessDataModel>, ErrorDataModel,
        SuccessMainScreenState, ErrorMainScreenState>() {

    override val receiveChannel: ReceiveChannel<Result<List<SuccessDataModel>, ErrorDataModel>>
        get() = getDataUseCase.receiveChannel

    companion object {
        internal const val TIMEOUT = 250L
    }

    override fun successState(result: List<SuccessDataModel>): SuccessMainScreenState {
        return SuccessMainScreenState(result)
    }

    override fun errorState(result: ErrorDataModel): ErrorMainScreenState {
        return ErrorMainScreenState(result.toString())
    }
}