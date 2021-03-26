package com.example.fromrxjavatocoroutinesflow.data.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("name")
    val name: String
)