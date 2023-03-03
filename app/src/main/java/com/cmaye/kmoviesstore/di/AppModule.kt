package com.cmaye.kmoviesstore.di


import com.cmaye.kmoviesstore.network.ApiInterface
import com.cmaye.kmoviesstore.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {
    fun provideRetrofit(baseUrl: String) : Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(ApiService.createHttpClient()).build()

    fun provideApiInterface(retrofit: Retrofit) : ApiInterface = retrofit.create(ApiInterface::class.java)
}

