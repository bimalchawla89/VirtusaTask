package com.example.task.ui.base

import com.example.task.util.coroutines.DispatcherProvider

open class BaseUseCase<R:BaseRepository>( private val repository: R, private val appDispatcher: DispatcherProvider) {

    fun getRepository():R{
        return repository
    }

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

}