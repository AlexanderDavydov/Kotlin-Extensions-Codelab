package com.empam.codelab.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T : Any> LifecycleOwner.observeIgnoreNull(data: LiveData<T>, func: (T) -> Unit) {
    data.observe(this, Observer<T> {
        it?.let { func(it) }
    })
}