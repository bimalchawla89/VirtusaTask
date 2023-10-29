package com.example.task.ui.facts

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task.data.network.Resource
import com.example.task.data.network.model.FactResponse
import com.example.task.ui.base.BaseViewModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FactsViewModel @Inject constructor(
    factsUseCase: FactsUseCase
) : BaseViewModelUseCase<FactsUseCase>(factsUseCase) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _factsData = MutableLiveData<Resource<FactResponse>>()
    val factsData: LiveData<Resource<FactResponse>> get() = _factsData

    init {
        getFactsData()
    }

    fun getFactsData() {
        viewModelScope.launch(exceptionHandler) {
            showLoading()
            val response = getUseCase().getFacts()
            _factsData.value = response
            hideLoading()
        }
    }
}