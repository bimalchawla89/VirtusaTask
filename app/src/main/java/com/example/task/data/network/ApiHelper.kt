package com.example.task.data.network

import com.example.task.data.network.model.FactResponse

interface ApiHelper {
    suspend fun getFacts(): Resource<FactResponse>
}
