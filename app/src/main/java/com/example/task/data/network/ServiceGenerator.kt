package com.example.task.data.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

const val timeoutRead = 30
const val timeoutConnect = 30

@Singleton
class ServiceGenerator  @Inject constructor(){

    @Inject
    lateinit var service : Service

    @Inject
    lateinit var retrofit: Retrofit
}