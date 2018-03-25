package com.annasblackhat.onlineshop

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by annasblackhat on 25/03/18.
 */

@BindingAdapter("imageUrl")
fun setImageUrl(img: ImageView, url: String){
    Picasso.get()
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(img)
}