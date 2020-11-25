package com.udacity.shoestore.extension

import android.widget.TextView
import androidx.lifecycle.MutableLiveData


fun TextView.asText() = this.text.toString()

fun TextView.asInt() = asText().toInt()

fun TextView.add(number: Int) {
    text = asInt().plus(number).toString()
}

fun TextView.subtract(number: Int) {
    text = asInt().minus(number).toString()
}

fun MutableLiveData<String>.add(number: Int) {
    value?.toInt()?.plus(number).toString().also { postValue(it) }
}

fun MutableLiveData<String>.subtract(number: Int) {
    value?.toInt()?.minus(number).toString().also { postValue(it) }
}