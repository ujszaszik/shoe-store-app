package com.udacity.shoestore.model

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

private const val TEXT_ATTRIBUTE = "android:text"

@BindingAdapter(TEXT_ATTRIBUTE)
fun setText(editText: TextView, value: Int) {
    var text = ""
    if (value > 0) text = value.toString()
    editText.text = (text)
}

@InverseBindingAdapter(attribute = TEXT_ATTRIBUTE)
fun getText(editText: TextView): Int {
    val value = editText.text.toString()
    return if (value.isNotEmpty()) value.toInt() else 0
}