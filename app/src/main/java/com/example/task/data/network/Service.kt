package com.example.task.data.network


import com.example.task.data.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("/api/facts?number=50")
    suspend fun getFacts():Response<FactResponse>
}
