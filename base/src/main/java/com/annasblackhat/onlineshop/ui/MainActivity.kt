package com.annasblackhat.onlineshop.ui

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.annasblackhat.onlineshop.R
import com.annasblackhat.onlineshop.app.OnlineShopApp
import com.annasblackhat.onlineshop.model.Product
import com.annasblackhat.onlineshop.util.GlobalAdapter
import com.annasblackhat.onlineshop.util.GlobalViewHolder
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
        recview_product.adapter = object : GlobalAdapter(R.layout.list_item_product, productList){
            override fun onBindViewHolder(holder: GlobalViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.itemView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.uniq.id/online-shop/detail/${productList[position].id}"))
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.`package` = packageName
                    println("Package : $packageName")
                    startActivity(intent)
                }
            }
        }

        progressBar.visibility = View.VISIBLE
        val service = (application as OnlineShopApp).getService()
        service.getProducts().enqueue(object : Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Failed to load data.\n${t?.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<Product>>?, response: Response<ArrayList<Product>>?) {
                productList.clear()
                response?.body()?.let { productList.addAll(it) }
                recview_product.adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }
}
