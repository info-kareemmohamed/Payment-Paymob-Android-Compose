package com.example.myapplication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.ClientSecretResult
import com.example.myapplication.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymobViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

    private val _state = mutableStateOf(PaymobState())
    val state: State<PaymobState> = _state

    fun onIntent(intent: PaymobIntent) {
        when (intent) {
            PaymobIntent.StartPayment -> startPayment()
            is PaymobIntent.OnAmountChanged -> {
                val filteredAmount = intent.amount.filter { it.isDigit() }
                _state.value = state.value.copy(amount = filteredAmount)
            }
        }
    }

    private fun startPayment() {
        val amountInt = _state.value.amount.toIntOrNull()
        if (amountInt == null || amountInt == 0) {
            _state.value = state.value.copy(errorMessage = "The amount can't be zero")
            return
        }

        viewModelScope.launch {
            repository.getClientSecret(amountInt).collect { result ->
                _state.value = when (result) {
                    is ClientSecretResult.Loading -> state.value.copy(
                        processLoadingState = true, errorMessage = null, clientSecret = null
                    )
                    is ClientSecretResult.Success -> state.value.copy(
                        clientSecret = result.data, processLoadingState = false, amount = ""
                    )
                    is ClientSecretResult.Error -> state.value.copy(
                        errorMessage = result.message, processLoadingState = false
                    )
                }
            }
        }
    }
}

data class PaymobState(
    val amount: String = "",
    val processLoadingState: Boolean = false,
    val clientSecret: String? = null,
    val errorMessage: String? = null
)

sealed class PaymobIntent {
    object StartPayment : PaymobIntent()
    data class OnAmountChanged(val amount: String) : PaymobIntent()
}
