package com.udacity.shoestore.keyboard

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

object HideKeyboardListener : View.OnTouchListener {

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        val inputMethodManager =
            view?.context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.findFocus().windowToken, 0);
        return true;
    }

}