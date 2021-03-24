package com.example.fromrxjavatocoroutinesflow.rx

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fromrxjavatocoroutinesflow.common.CommonMainFragment
import com.example.fromrxjavatocoroutinesflow.common.CommonViewModel
import com.example.fromrxjavatocoroutinesflow.common.ViewModelFactory

class MainFragmentRx : CommonMainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Rx"
    }

    override fun model(): CommonViewModel {
        return ViewModelProvider(requireActivity(), ViewModelFactory(searchView))
            .get(ViewModelRx::class.java)
    }

}