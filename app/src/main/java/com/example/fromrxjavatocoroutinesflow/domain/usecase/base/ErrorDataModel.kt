package com.example.fromrxjavatocoroutinesflow.domain.usecase.base

sealed class ErrorDataModel {

    object NetworkError : ErrorDataModel()
    object ResponseError : ErrorDataModel()
}