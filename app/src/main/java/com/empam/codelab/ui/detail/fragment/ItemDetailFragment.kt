package com.empam.codelab.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.empam.codelab.R
import com.empam.codelab.extensions.observeIgnoreNull
import com.empam.codelab.ui.detail.ItemDetailFactory
import com.empam.codelab.ui.detail.ItemDetailViewModel
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    lateinit var vm: ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProviders.of(this, ItemDetailFactory(requireContext()))[ItemDetailViewModel::class.java]

        arguments?.let { vm.handleFragmentArguments(it) }

        vm.titleStream.observe(this, Observer<String> {
            activity?.toolbar_layout?.title = it
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        vm.titleStream.observe(this, Observer<String> {
//            it?.let { item_detail.text = it }
//        })

        observeIgnoreNull(vm.titleStream) {
            it?.let { item_detail.text = it }
        }

        observeIgnoreNull(vm.favMarkerStream) {
            if (it) {
                requireActivity().fab.setImageResource(R.drawable.ic_fav_on)
            } else {
                requireActivity().fab.setImageResource(R.drawable.ic_fav_off)
            }
        }

        requireActivity().fab.setOnClickListener { view ->
            vm.onFavoritesClicked()

//            view.showSnackbarWithUndo("Action") {
//
//            }
        }

    }

}
