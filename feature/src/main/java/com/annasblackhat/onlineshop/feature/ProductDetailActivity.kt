package com.annasblackhat.onlineshop.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.annasblackhat.onlineshop.app.OnlineShopApp
import com.annasblackhat.onlineshop.model.Product
import com.annasblackhat.onlineshop.util.setImageUrl
import kotlinx.android.synthetic.main.activity_product_detail.*
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        handleAppLink()

        imageView4.setOnClickListener { Toast.makeText(this, "This item has been added to your cart", Toast.LENGTH_SHORT).show() }
        button.setOnClickListener { Toast.makeText(this, "Thanks for buying this product!", Toast.LENGTH_SHORT).show() }
    }

    private fun handleAppLink() {
        intent?.data?.takeIf { intent.data.pathSegments.size > 1 }?.let {
            loadProductDetail(intent.data.pathSegments[2])
        } ?: kotlin.run { loadProductDetail("1") }
    }

    private fun loadProductDetail(id: String) {
        val service = (application as OnlineShopApp).getService()
        service.getProducts().enqueue(object : Callback<ArrayList<Product>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Product>>?, t: Throwable?) {
                Toast.makeText(this@ProductDetailActivity, "Failed to load data. \n"+t?.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: retrofit2.Call<ArrayList<Product>>?, response: Response<ArrayList<Product>>?) {
               response?.body()?.firstOrNull { it.id.toString() == id}?.let { product ->
                   txt_title.text = product.product
                   txt_desc.text = product.description
                   txt_price.text = "$"+product.price
                   setImageUrl(imageView3, product.images?.get(0))
               }
            }
        })
    }
}
