package com.annasblackhat.onlineshop.util

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annasblackhat.onlineshop.BR

/**
 * Created by ANNASBlackHat on 13/09/2017.
 */

abstract class GlobalAdapter(@field:LayoutRes private var layoutId: Int,
                             private val listData: List<*>? = null, private val viewNoData: View? = null) :
        RecyclerView.Adapter<GlobalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GlobalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GlobalViewHolder, position: Int) {
        listData?.apply {  holder.onBind(BR.model, listData[position]) }
    }

    override fun getItemCount(): Int {
        viewNoData?.let { it.visibility = if(listData?.isNotEmpty() == true) View.GONE else View.VISIBLE }
        return listData?.size ?: 0
    }

    fun changeLayout(layoutId: Int){
        this.layoutId = layoutId
    }

    fun removeItem(viewholder: GlobalViewHolder? = null){
        if(viewholder != null){
            if(listData is ArrayList && listData.isNotEmpty()){
                listData.remove(listData[viewholder.layoutPosition])
                notifyItemRemoved(viewholder.layoutPosition)
            }
        }else{
            if(listData is ArrayList){
                listData.clear()
                notifyDataSetChanged()
            }
        }
    }

}
