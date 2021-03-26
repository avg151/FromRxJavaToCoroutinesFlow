package com.example.fromrxjavatocoroutinesflow.data.repository

import android.util.Log
import com.example.fromrxjavatocoroutinesflow.data.response.DataResponse
import com.example.fromrxjavatocoroutinesflow.data.service.DataService
import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.repository.DataRepository
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.ErrorDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import retrofit2.Response

class DataRepositoryImpl constructor(private val dataService: DataService) : DataRepository {

    override suspend fun getData(param: String): Result<List<SuccessDataModel>, ErrorDataModel> {
        return try {
            val response: Response<List<DataResponse>> = dataService.getData(param)

            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(convert(body))
            } else {
                Result.Error(ErrorDataModel.ResponseError)
            }
        } catch (error: Exception) {
            Log.e("DataRepositoryImpl", error.toString())
            Result.Error(ErrorDataModel.NetworkError)
        }
    }

    private fun convert(body: List<DataResponse>): List<SuccessDataModel> {
        return body.map { SuccessDataModel(it.name) }.take(10)
    }

}