package com.example.fromrxjavatocoroutinesflow.presentation.view.flow

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.R
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonMainFragment
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonViewModel
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.ViewModelFactory
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

class MainFragmentFlow : CommonMainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = getString(R.string.flowTitle)
    }

    @FlowPreview
    override fun model(): CommonViewModel {
        val getDataUseCase: GetDataUseCase by inject()
        return ViewModelProvider(requireActivity(), ViewModelFactory(searchView, getDataUseCase))
            .get(ViewModelFlow::class.java)
    }

}