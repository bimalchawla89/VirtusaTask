package com.example.task.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FactResponse(
    @Json(name = "facts")
    val facts: List<String> = listOf(),
    @Json(name = "success")
    val status: Boolean = false
)