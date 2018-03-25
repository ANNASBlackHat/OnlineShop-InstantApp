package com.annasblackhat.onlineshop.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.annasblackhat.onlineshop.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val data = intent.getSerializableExtra("product")
        if(data is Product){
            txt_title.text = data.product
            txt_desc.text = data.description
            txt_price.text = "$"+data.price
        }

        imageView4.setOnClickListener { Toast.makeText(this, "This item has been added to your cart", Toast.LENGTH_SHORT).show() }
        button.setOnClickListener { Toast.makeText(this, "Thanks for buying this product!", Toast.LENGTH_SHORT).show() }
    }
}
