package com.example.fromrxjavatocoroutinesflow.domain.usecase

import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.repository.DataRepository
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.BaseUseCase
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result

class GetDataUseCase(private val dataRepository: DataRepository) :
    BaseUseCase<String, List<SuccessDataModel>>() {

    override suspend fun run(params: String) {
        resultChannel.send(Result.Loading)
        resultChannel.send(dataRepository.getData(params))
    }

}