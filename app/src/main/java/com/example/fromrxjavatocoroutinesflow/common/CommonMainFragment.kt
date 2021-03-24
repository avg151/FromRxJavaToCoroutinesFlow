package com.example.fromrxjavatocoroutinesflow.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.fromrxjavatocoroutinesflow.R


abstract class CommonMainFragment : Fragment(R.layout.fragment_main) {

    protected lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view)
        model().getData().observe(requireActivity(), { data ->

        })
    }


    abstract fun model(): CommonViewModel
}