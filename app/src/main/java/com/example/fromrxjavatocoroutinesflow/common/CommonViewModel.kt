package com.example.fromrxjavatocoroutinesflow.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class CommonViewModel : ViewModel() {

    protected val data: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    fun getData(): LiveData<List<String>> {
        return data
    }

    companion object {
        internal const val TIMEOUT = 250L
    }

}