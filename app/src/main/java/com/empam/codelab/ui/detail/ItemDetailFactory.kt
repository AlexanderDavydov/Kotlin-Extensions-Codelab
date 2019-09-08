package com.empam.codelab.ui.detail

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemDetailFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ItemDetailViewModel::class.java)) {
            return ItemDetailViewModel(PreferenceManager.getDefaultSharedPreferences(context)) as T
        }

        throw RuntimeException("Can't create View Model Class")
    }

}
