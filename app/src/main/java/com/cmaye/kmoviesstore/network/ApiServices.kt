//package com.cmaye.kmoviesstore.network
//
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Protocol
//import okhttp3.Request
//import okhttp3.logging.HttpLoggingInterceptor
//import java.util.concurrent.TimeUnit
//import kotlin.math.log
//
//object ApiService {
//    private const val CONTENT_TYPE = "Content-Type"
//    private const val APPLICATION_JSON = "application/json"
//    private fun createHeaderInterceptor() : Interceptor{
//        return Interceptor{ chain ->
//            val newRequest = chain.request().newBuilder().header(CONTENT_TYPE, APPLICATION_JSON).build()
//            chain.proceed(newRequest)
//
//        }
//    }
//    private fun createHeaderInterceptor(token: String) : Interceptor{
//        return Interceptor{ chain ->
//        var newRequest : Request = chain.request().newBuilder().header(CONTENT_TYPE,
//            APPLICATION_JSON).header("Authorization","Bearer $token").build()
//            chain.proceed(newRequest)
//        }
//    }
//    private fun createLoggingInterceptor() : HttpLoggingInterceptor{
//        var loginInterceptor = HttpLoggingInterceptor()
//        loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return loginInterceptor
//    }
//    fun createHttpClient() : OkHttpClient{
//        return OkHttpClient.Builder().apply {
//            addNetworkInterceptor(createHeaderInterceptor())
//            protocols(listOf(Protocol.HTTP_1_1))
//            connectTimeout(180,TimeUnit.SECONDS)
//            readTimeout(180,TimeUnit.SECONDS)
//            writeTimeout(180,TimeUnit.SECONDS)
//        }.build()
//    }
//
//
//
//    fun createHttpClient(token: String): OkHttpClient {
//        return OkHttpClient.Builder().apply {
//            addNetworkInterceptor(createHeaderInterceptor(token))
//            addInterceptor(createLoggingInterceptor())
//            protocols(listOf(Protocol.HTTP_1_1))
//            connectTimeout(60, TimeUnit.SECONDS)
//            readTimeout(60, TimeUnit.SECONDS)
//            writeTimeout(60, TimeUnit.SECONDS)
//        }.build()
//    }
//
//}