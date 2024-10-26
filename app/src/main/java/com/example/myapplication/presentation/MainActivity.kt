package com.example.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.data.Constants.PUBLIC_KEY
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.paymob.paymob_sdk.PaymobSdk
import com.paymob.paymob_sdk.ui.PaymobSdkListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymobSdkListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MyApplicationTheme {
                    val viewModel = hiltViewModel<PaymobViewModel>()
                    LaunchedEffect(viewModel.state.value.clientSecret) {
                        viewModel.state.value.clientSecret?.let { makePayment(it) }
                    }

                    PaymentScreen(
                        processLoadingState = viewModel.state.value.processLoadingState,
                        errorMessage = viewModel.state.value.errorMessage,
                        amount = viewModel.state.value.amount,
                        onAmountChanged = { viewModel.onIntent(PaymobIntent.OnAmountChanged(it)) },
                        onPayClicked = { viewModel.onIntent(PaymobIntent.StartPayment) }
                    )
                }
            }
        }
    }


    private fun makePayment(clientSecret: String) {
        PaymobSdk.Builder(
            context = this,
            clientSecret = clientSecret,
            publicKey = PUBLIC_KEY,
            paymobSdkListener = this
        )
            .build()
            .start()
    }


    // You can use ViewModel or other code to handle the payment state more effectively

    override fun onFailure() {
        Log.d("PaymobSdkListener", "onFailure")
    }

    override fun onPending() {
        Log.d("PaymobSdkListener", "onPending")
    }

    override fun onSuccess() {
        Log.d("PaymobSdkListener", "onSuccess")
    }


}

