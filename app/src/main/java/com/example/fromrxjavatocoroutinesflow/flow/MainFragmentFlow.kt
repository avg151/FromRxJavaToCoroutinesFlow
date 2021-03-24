package com.example.fromrxjavatocoroutinesflow.flow

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.common.CommonMainFragment
import com.example.fromrxjavatocoroutinesflow.common.CommonViewModel
import com.example.fromrxjavatocoroutinesflow.common.ViewModelFactory

class MainFragmentFlow : CommonMainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Flow"
    }

    override fun model(): CommonViewModel {
        return ViewModelProvider(requireActivity(), ViewModelFactory(searchView))
            .get(ViewModelFlow::class.java)
    }

}