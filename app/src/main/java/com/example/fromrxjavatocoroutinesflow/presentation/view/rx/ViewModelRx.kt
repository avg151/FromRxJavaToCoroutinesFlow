package com.example.fromrxjavatocoroutinesflow.presentation.view.rx

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import com.example.fromrxjavatocoroutinesflow.presentation.view.common.CommonViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class ViewModelRx(
    searchView: SearchView,
    getData: GetDataUseCase
) : CommonViewModel(getData) {

    init {
        Observable.create(observableOnSubscribe(searchView))
            .debounce(TIMEOUT, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .map { it.toLowerCase(Locale.ROOT).trim() }
            .filter { it.isNotBlank() }
            .subscribe { getData(it) }
    }

    private fun observableOnSubscribe(view: SearchView) =
        ObservableOnSubscribe<String> { subscriber ->
            view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { subscriber.onNext(it) }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { subscriber.onNext(it) }
                    return false
                }
            })
        }

}