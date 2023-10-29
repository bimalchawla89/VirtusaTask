package com.example.task.data.repos

import com.example.task.data.network.ApiHelper
import com.example.task.data.network.Resource
import com.example.task.data.network.model.FactResponse
import com.example.task.ui.base.BaseRepository
import com.example.task.util.coroutines.DispatcherProvider
import javax.inject.Inject

class FactRepository @Inject constructor(
    appDispatcher: DispatcherProvider,
    apiHelper: ApiHelper,
) : BaseRepository(appDispatcher,apiHelper) {

    suspend fun getFacts(): Resource<FactResponse> {
        return getApiHelper().getFacts()
    }
}