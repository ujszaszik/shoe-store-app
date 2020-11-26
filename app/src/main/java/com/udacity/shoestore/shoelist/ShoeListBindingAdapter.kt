package com.udacity.shoestore.shoelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListRowBinding
import com.udacity.shoestore.model.Shoe

class ShoeListBindingAdapter(
    private val context: Context,
    private val fragment: Fragment,
    private val shoes: Set<Shoe>
) {

    companion object {
        private const val BIND_CREATE_VIEW = "bind:createView"

        @JvmStatic
        @BindingAdapter(BIND_CREATE_VIEW)
        fun createView(layout: FrameLayout, replacement: ScrollView?) {
            layout.removeAllViews()
            layout.addView(replacement)
        }
    }

    fun getDynamicView(): ScrollView? {
        val scrollView = ScrollView(context)
        val view = if (shoes.isEmpty()) getEmptyListView() else getListView()
        scrollView.addView(view)
        return scrollView
    }

    private fun getListView(): View {
        val linearLayout = getLinearLayout()
        shoes.forEach {
            val rowBinding = ShoeListRowBinding.inflate(
                LayoutInflater.from(context),
                fragment.view?.parent as ViewGroup,
                false
            )
            rowBinding.shoe = it
            linearLayout.addView(rowBinding.root)
        }
        return linearLayout
    }

    private fun getLinearLayout(): LinearLayout {
        return LinearLayout(context)
            .apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
            }
    }

    private fun getEmptyListView(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.shoe_not_added_layout, fragment.view?.parent as ViewGroup, false)
    }

}