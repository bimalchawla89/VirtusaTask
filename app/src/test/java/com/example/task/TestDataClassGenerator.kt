package com.example.task

import com.example.task.data.network.DataError
import com.example.task.data.network.Resource
import com.example.task.data.network.Success
import com.example.task.data.network.model.FactResponse
import com.example.task.util.NO_INTERNET_CONNECTION
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File


class TestDataClassGenerator {

    val moshi = Moshi.Builder().build()
    //generic function to  generate data classes from json file path
    private inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return jsonAdapter.fromJson(json)!!
    }

    fun getSuccessFactsResponse(): Resource<FactResponse> {
        val jsonString = getJson("FactsApiResponse.json")
        return Success(buildDataClassFromJson(jsonString))
    }

    fun getFailedFactsResponse():Resource<FactResponse> {
        val jsonString = getJson("FactsFailedApiResponse.json")
        return Success(buildDataClassFromJson(jsonString))
    }

    fun getNoNetworkError():Resource<FactResponse> {
        return DataError(NO_INTERNET_CONNECTION)
    }

    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/$resourceName")
        return String(file.readBytes())
    }
}
