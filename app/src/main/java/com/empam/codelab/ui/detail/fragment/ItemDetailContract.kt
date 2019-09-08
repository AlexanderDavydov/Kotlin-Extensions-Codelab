package com.empam.codelab.ui.detail.fragment

import androidx.lifecycle.LiveData

interface ItemDetailContract {

    interface ViewModel: Streams {

    }

    interface Streams {

        val titleStream: LiveData<String>
        val detailsStream: LiveData<String>
        val favMarkerStream: LiveData<Boolean>

    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }


}
