package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<DataModel, ErrorModel, SuccessScreenState, ErrorScreenState> :
    ViewModel(),
    CoroutineScope {

    private val job = Job()
    protected abstract val receiveChannel: ReceiveChannel<Result<DataModel, ErrorModel>>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val screenState = MutableLiveData<Result<SuccessScreenState, ErrorScreenState>>()
    fun screenState(): LiveData<Result<SuccessScreenState, ErrorScreenState>> = screenState

    init {
        launch {
            receiveChannel.consumeEach {
                it.handleResult(::handleLoading, ::handleSuccess, ::handleFailure)
            }
        }
    }

    private fun handleLoading(result: Result.Loading) {
        screenState.postValue(result)
    }

    private fun handleSuccess(result: DataModel) {
        screenState.postValue(Result.Success(successState(result)))
    }

    private fun handleFailure(result: ErrorModel) {
        screenState.postValue(Result.Error(errorState(result)))
    }

    abstract fun successState(result: DataModel): SuccessScreenState

    abstract fun errorState(result: ErrorModel): ErrorScreenState

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}