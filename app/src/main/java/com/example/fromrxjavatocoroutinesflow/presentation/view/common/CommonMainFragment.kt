package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.fromrxjavatocoroutinesflow.R
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result.*
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.ErrorMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState


abstract class CommonMainFragment : Fragment(R.layout.fragment_main) {

    protected lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view)
        model().screenState().observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(screenState: Result<SuccessMainScreenState, ErrorMainScreenState>) {
        when (screenState) {
            is Loading -> renderLoading()
            is Success -> renderSuccess(screenState.successData)
            is Error -> renderError(screenState.errorData)
        }
    }

    private fun renderLoading() {

    }

    private fun renderSuccess(state: SuccessMainScreenState) {

    }

    private fun renderError(state: ErrorMainScreenState) {

    }

    abstract fun model(): CommonViewModel
}