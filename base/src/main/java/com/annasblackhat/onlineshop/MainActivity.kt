package com.annasblackhat.onlineshop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.annasblackhat.betteradapter.BetterAdapter
import com.annasblackhat.betteradapter.BetterViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recview_product.layoutManager = GridLayoutManager(this, 2)
        recview_product.adapter = object : BetterAdapter(R.layout.list_item_product, productList, BR.model){
            override fun onBindViewHolder(holder: BetterViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.itemView.setOnClickListener {
                    val intent = Intent(this@MainActivity, Class.forName("com.annasblackhat.onlineshop.feature.ProductDetailActivity"))
                    intent.putExtra("product", productList[position])
                    startActivity(intent)
                }
            }
        }

        val service = (application as OnlineShopApp).getService()
        service.getProducts().enqueue(object : Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>?, t: Throwable?) {
                println("Fail.... $t")
            }

            override fun onResponse(call: Call<ArrayList<Product>>?, response: Response<ArrayList<Product>>?) {
                productList.clear()
                response?.body()?.let { productList.addAll(it) }
                recview_product.adapter.notifyDataSetChanged()
            }

        })
    }
}
