package com.example.fromrxjavatocoroutinesflow.presentation.view.common

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.fromrxjavatocoroutinesflow.R
import com.example.fromrxjavatocoroutinesflow.domain.models.SuccessDataModel
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result
import com.example.fromrxjavatocoroutinesflow.domain.usecase.base.Result.*
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.ErrorMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState.SuccessEmptyMainScreenState
import com.example.fromrxjavatocoroutinesflow.presentation.view.state.MainScreenState.SuccessMainScreenState.SuccessNotEmptyMainScreenState


abstract class CommonMainFragment : Fragment(R.layout.fragment_main) {

    protected lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    private var adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view)
        progressBar = view.findViewById(R.id.progress_bar)
        textView = view.findViewById(R.id.message_text_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter

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
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        textView.visibility = View.INVISIBLE
    }

    private fun renderSuccess(state: SuccessMainScreenState) {
        when (state) {
            is SuccessEmptyMainScreenState -> message(getString(state.emptyMessage))
            is SuccessNotEmptyMainScreenState -> renderSuccessNotEmpty(state.dataList)
        }
    }

    private fun renderSuccessNotEmpty(data: List<SuccessDataModel>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE

        adapter.setData(data)
    }

    private fun renderError(state: ErrorMainScreenState) {
        message(state.errorMessage)
    }

    private fun message(message: String) {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
        textView.visibility = View.VISIBLE

        textView.text = message
    }

    abstract fun model(): CommonViewModel
}