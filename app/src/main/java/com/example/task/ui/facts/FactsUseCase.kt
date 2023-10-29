package com.example.task.ui.facts

import com.example.task.data.network.Resource
import com.example.task.data.network.model.FactResponse
import com.example.task.data.repos.FactRepository
import com.example.task.ui.base.BaseUseCase
import com.example.task.util.coroutines.DispatcherProvider
import javax.inject.Inject

class FactsUseCase @Inject constructor(
    appDispatcher: DispatcherProvider,
    factRepository: FactRepository
) : BaseUseCase<FactRepository>(factRepository, appDispatcher) {

    suspend fun getFacts(): Resource<FactResponse>{
        return getRepository().getFacts()
    }
}