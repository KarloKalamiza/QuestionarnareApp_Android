package hr.algebra.questionarnareapp.Utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = this.currentFocus ?:  View(this)
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(text: String, length: Int)
= Toast.makeText(this, text, length).show()