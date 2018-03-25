package com.annasblackhat.onlineshop

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by annasblackhat on 25/03/18.
 */

class OnlineShopApp: Application() {

    private lateinit var service: AppService

    companion object {
        val BASE_URL = "https://raw.githubusercontent.com/ANNASBlackHat/OnlineShop---InstantApp/master/json/"
    }

    override fun onCreate() {
        super.onCreate()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS)
        okHttpClient.readTimeout(15, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())

        var retrofit = builder.baseUrl(BASE_URL).build()
        service = retrofit.create(AppService::class.java)

    }

    fun getService() = service
}