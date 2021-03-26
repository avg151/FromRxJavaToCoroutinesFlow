package com.example.fromrxjavatocoroutinesflow.presentation.view.flow

import androidx.appcompat.widget.SearchView
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

@FlowPreview
class ViewModelFlow(
    searchView: SearchView,
    getData: GetDataUseCase
) : CommonViewModel(getData) {

    init {
        launch {
            searchView
                .getQueryTextChangeStateFlow()
                .debounce(TIMEOUT)
                .distinctUntilChanged()
                .map { text -> text.toLowerCase(Locale.ROOT).trim() }
                .filter { text -> text.isNotBlank() }
                .collect { result -> getData(result) }
        }
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
        val query = MutableStateFlow("")
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                if (newText != null) {
                    query.value = newText
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })
        return query
    }

}
