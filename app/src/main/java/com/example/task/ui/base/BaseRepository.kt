package com.example.task.ui.base

import com.example.task.data.network.ApiHelper
import com.example.task.util.coroutines.DispatcherProvider


open class BaseRepository(
    private val appDispatcher: DispatcherProvider,
    private val apiHelper: ApiHelper,
) {

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

    fun getApiHelper(): ApiHelper {
        return apiHelper
    }
}