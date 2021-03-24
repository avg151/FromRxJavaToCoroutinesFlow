package com.example.fromrxjavatocoroutinesflow.rx

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fromrxjavatocoroutinesflow.R

class MainFragmentRx : Fragment(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Rx"
    }

}