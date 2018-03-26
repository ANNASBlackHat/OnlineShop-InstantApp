package com.annasblackhat.onlineshop.util

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Git Solution on 13/09/2017.
 */

class GlobalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewDataBinding? = null

    init {
        try {
            binding = DataBindingUtil.bind(itemView)
        } catch (e: Exception) {
        }

    }

    fun onBind(variable: Int, value: Any?) {
        value?.let {
            binding?.setVariable(variable, value)
            binding?.executePendingBindings()
        }
    }
}
