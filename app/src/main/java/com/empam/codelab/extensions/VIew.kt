package com.empam.codelab.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}


fun View.showSnackbarWithUndo(text: String, onClick: (View) -> Unit) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        .setAction("Undo") { onClick(it) }
        .show()
}