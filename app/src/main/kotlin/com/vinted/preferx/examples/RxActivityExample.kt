package com.vinted.preferx.examples

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.vinted.preferx.stringPreference
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_rx.*


class RxActivityExample : Activity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("app-preferences", Context.MODE_PRIVATE)
    }

    private val stringPreference by lazy {
        sharedPreferences.stringPreference("rx-counter", "")
    }

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        input.setText(stringPreference.get())
        input.addTextChangedListener(createWatcher())

        Observable.just(stringPreference.get())
                .concatWith(stringPreference.onChangeObservable)
                .subscribe {
                    if (it.isNullOrBlank()) {
                        output.text = "Preference is empty"
                    } else {
                        output.text = it
                    }

                }.apply { disposable = this }
    }

    private fun createWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                stringPreference.set(s?.toString().orEmpty())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}