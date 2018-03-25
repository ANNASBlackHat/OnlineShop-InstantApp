package com.annasblackhat.onlineshop

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by annasblackhat on 25/03/18.
 */
interface AppService {

    @GET("product.json")
    fun getProducts(): Call<ArrayList<Product>>

}