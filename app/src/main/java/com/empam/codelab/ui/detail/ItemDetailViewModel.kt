package com.empam.codelab.ui.detail

import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.empam.codelab.dummy.DummyContent
import com.empam.codelab.ui.detail.fragment.ItemDetailContract
import com.empam.codelab.ui.detail.fragment.ItemDetailContract.Companion.ARG_ITEM_ID

class ItemDetailViewModel(val prefs: SharedPreferences) : ViewModel(),
    ItemDetailContract.ViewModel {

    companion object {
        const val KEY_FAVORITES = "key_favorites"
    }

    override val titleStream = MutableLiveData<String>()
    override val detailsStream = MutableLiveData<String>()
    override val favMarkerStream = MutableLiveData<Boolean>()


    val favList = prefs.getStringSet(KEY_FAVORITES, mutableSetOf())!!

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: DummyContent.DummyItem? = null


    fun handleFragmentArguments(args: Bundle) {
        // Load content
        if (args.containsKey(ARG_ITEM_ID)) {
            item = DummyContent.ITEM_MAP[args.getString(ARG_ITEM_ID)]
            titleStream.value = item?.content
            detailsStream.value = item?.details

            favMarkerStream.value = favList.contains(item?.id)
        }
    }

    fun onFavoritesClicked() {
        item?.let {
            if (favList.contains(it.id).not()) {
                addToFavorites(it.id)
            } else {
                removeFromFavorites(it.id)
            }
        }
    }

    private fun addToFavorites(id: String) {
        favList.add(id)
        updateFavStorage()
        favMarkerStream.value = true
    }

    private fun removeFromFavorites(id: String) {
        favList.remove(id)
        updateFavStorage()
        favMarkerStream.value = false
    }

    private fun updateFavStorage() {
        prefs.edit { this.putStringSet(KEY_FAVORITES, favList) }
    }

}