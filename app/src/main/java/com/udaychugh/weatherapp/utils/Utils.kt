package com.udaychugh.weatherapp.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.String.format
import java.text.DecimalFormat
import java.util.logging.Level.parse

fun convertKelvinToCelsius(number: Number): Double {
    return DecimalFormat().run {
        applyPattern(".##")
        parse(format(number.toDouble().minus(273))).toDouble()
    }
}

inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    visibility = if (condition(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(
        lifecycleOwner,
        object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        }
    )
}
