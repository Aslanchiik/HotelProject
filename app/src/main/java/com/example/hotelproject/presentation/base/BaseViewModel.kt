package com.example.hotelproject.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.Either
import com.example.domain.utils.NetworkError
import com.example.hotelproject.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    protected fun <T> Flow<Either<NetworkError, T>>.collectRequest(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(it.value)
                }
            }
        }
    }

    protected fun <T, S> Flow<Either<NetworkError, T>>.collectRequest(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(mappedData(it.value))
                }
            }
        }
    }

    protected fun <T, S> Flow<List<T>>.collectLocalRequestForList(
        mapToUI: (T) -> S
    ): Flow<List<S>> = map { value: List<T> ->
        value.map { data: T ->
            mapToUI(data)
        }
    }
}