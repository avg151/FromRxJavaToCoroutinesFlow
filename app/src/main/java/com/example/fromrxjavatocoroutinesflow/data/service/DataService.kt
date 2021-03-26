package com.example.fromrxjavatocoroutinesflow.data.service

import com.example.fromrxjavatocoroutinesflow.data.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("search")
    suspend fun getData(@Query("country") q: String): Response<List<DataResponse>>

}