package com.example.fromrxjavatocoroutinesflow.domain.usecase.base

sealed class Result<out S, out E> {
    object Loading : Result<Nothing, Nothing>()
    class Success<out S>(val successData: S) : Result<S, Nothing>()
    class Error<out E>(val errorData: E) : Result<Nothing, E>()

    fun handleResult(
        loadingBlock: (Loading) -> Unit = {},
        successBlock: (S) -> Unit = {},
        errorBlock: (E) -> Unit = {}
    ) {
        when (this) {
            is Loading -> loadingBlock(this)
            is Success -> successBlock(successData)
            is Error -> errorBlock(errorData)
        }
    }
}