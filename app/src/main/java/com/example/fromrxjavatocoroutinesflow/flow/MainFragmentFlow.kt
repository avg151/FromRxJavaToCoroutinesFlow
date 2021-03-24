package com.example.fromrxjavatocoroutinesflow.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fromrxjavatocoroutinesflow.R

class MainFragmentFlow : Fragment(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Flow"


    }

}