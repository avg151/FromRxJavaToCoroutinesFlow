package com.example.fromrxjavatocoroutinesflow.presentation.view.rx

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonMainFragment
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonViewModel
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.ViewModelFactory
import org.koin.android.ext.android.inject

class MainFragmentRx : CommonMainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Rx"
    }

    override fun model(): CommonViewModel {
        val getDataUseCase: GetDataUseCase by inject()
        return ViewModelProvider(requireActivity(), ViewModelFactory(searchView, getDataUseCase))
            .get(ViewModelRx::class.java)
    }

}