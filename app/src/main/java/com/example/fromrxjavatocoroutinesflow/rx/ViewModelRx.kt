package com.example.fromrxjavatocoroutinesflow.rx

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView
import com.example.fromrxjavatocoroutinesflow.common.CommonViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class ViewModelRx(searchView: SearchView) : CommonViewModel() {

    init {
        Observable.create(observableOnSubscribe(searchView))
            .map { text -> text.toLowerCase(Locale.ROOT).trim() }
            .debounce(TIMEOUT, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { text -> text.isNotBlank() }
            .subscribe { text -> data.postValue(listOf(text)) }
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