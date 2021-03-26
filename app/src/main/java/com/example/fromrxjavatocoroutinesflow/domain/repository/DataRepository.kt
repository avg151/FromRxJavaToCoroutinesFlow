package com.example.fromrxjavatocoroutinesflow.domain.repository

import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.ErrorDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result

interface DataRepository {

    suspend fun getData(param: String): Result<List<SuccessDataModel>, ErrorDataModel>

}