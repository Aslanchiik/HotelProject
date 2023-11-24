package com.example.hotelproject.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.domain.utils.NetworkError
import com.example.hotelproject.presentation.extensions.showToastShort
import com.example.hotelproject.presentation.state.UIState
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupRequests()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribers() {
    }

    private fun collectFlowSafely(
        lifecycleState: Lifecycle.State, collect: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect()
            }
        }
    }

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: NetworkError) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        collectFlowSafely(lifecycleState) {
            this.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> onError.invoke(it.error)
                    is UIState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    fun NetworkError.setupApiErrors(vararg inputs: TextInputLayout) = when (this) {
        is NetworkError.Unexpected -> {
            showToastShort(this.error)
        }

        is NetworkError.Api -> {
            showToastShort(this.error)
        }

        is NetworkError.ApiInputs -> {
            for (input in inputs) {
                error[input.tag].also { error ->
                    if (error == null) {
                        input.isErrorEnabled = false
                    } else {
                        input.error = error.joinToString()
                        this.error.remove(input.tag)
                    }
                }
            }
        }
    }
}
