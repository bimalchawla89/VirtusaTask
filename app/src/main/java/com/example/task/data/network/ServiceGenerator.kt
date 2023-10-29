package com.example.task.data.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

const val timeoutRead = 30
const val timeoutConnect = 30
const val baseUrl="https://dog-api.kinduff.com/"

@Singleton
class ServiceGenerator  @Inject constructor(){

    @Inject
    lateinit var service : Service

    @Inject
    lateinit var retrofit: Retrofit
}