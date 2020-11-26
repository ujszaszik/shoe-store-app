package com.udacity.shoestore.keyboard

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

object HideKeyboardListener : View.OnTouchListener {

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        getInputMethodManager(view)?.let { inputMethodManager ->
            view?.findFocus()?.let { focus ->
                inputMethodManager.hideSoftInputFromWindow(focus.windowToken, 0);
                return true;
            }
        } ?: return false
    }

    private fun getInputMethodManager(view: View?): InputMethodManager? {
        return view?.context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
    }

}