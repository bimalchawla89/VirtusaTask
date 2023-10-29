package com.example.task.di

import com.example.task.data.network.ApiHelper
import com.example.task.data.network.AppApiHelper
import com.example.task.data.repos.FactRepository
import com.example.task.ui.base.BaseRepository
import com.example.task.util.coroutines.AppDispatcherProvider
import com.example.task.util.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper

    @Binds
    @Singleton
    abstract fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider

    @Binds
    @Singleton
    abstract fun provideFactRepo(factRepository: FactRepository): BaseRepository

}