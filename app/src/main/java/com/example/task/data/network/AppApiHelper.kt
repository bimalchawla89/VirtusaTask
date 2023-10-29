package com.example.task.data.network

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import com.example.task.data.network.model.FactResponse
import com.example.task.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class AppApiHelper @Inject constructor(
    @ApplicationContext val context: Context,
) : ApiHelper {

    @Inject
    lateinit var serviceGenerator: ServiceGenerator

    override suspend fun getFacts(): Resource<FactResponse> {
        val service = serviceGenerator.service

        return when (val response = processCall { service.getFacts() }) {
            is FactResponse -> Success(response)
            else -> DataError(response as String)
        }
    }

    @VisibleForTesting(otherwise = PRIVATE)
    private inline fun processCall(responseCall: () -> Response<*>): Any? {
        if (!NetworkUtils.isNetworkAvailable(context)) {

            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                getResponseCodeString(responseCode)
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    private fun getResponseCodeString(responseCode: Int): String {
        if (responseCode in 400..499) {
            return CLIENT_SIDE_ERROR
        } else if (responseCode in 500..599) {
            return SERVER_SIDE_ERROR
        }
        return SOMETHING_WENT_WRONG
    }

}