package com.udacity.shoestore.extension

import android.widget.TextView


fun TextView.asText() = this.text.toString()

fun TextView.asInt() = asText().toInt()
