package com.example.myapplication.data.repository

import com.example.myapplication.data.Constants.ONLINE_CARD_PAYMENT_METHOD_ID
import com.example.myapplication.data.remote.PaymentService
import com.example.myapplication.data.remote.dto.BillingData
import com.example.myapplication.data.remote.dto.Customer
import com.example.myapplication.data.remote.dto.Item
import com.example.myapplication.data.remote.dto.PaymentRequest
import com.example.myapplication.domain.ClientSecretResult
import com.example.myapplication.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(private val api: PaymentService) :
    PaymentRepository {
    override fun getClientSecret(
        amount: Int,
        currency: String
    ): Flow<ClientSecretResult<String>> = flow {
        emit(ClientSecretResult.Loading())
        val clientSecret = api.getClientSecret(
            PaymentRequest(
                amount = amount * 100,// Multiply by 100 to handle amount in cents
                currency = currency,
                payment_methods = listOf(ONLINE_CARD_PAYMENT_METHOD_ID),//Add All Payment Methods ID you want to use
                items = listOf(Item("Item name 1", amount * 100, "Item description 1", 1)),
                billing_data = BillingData(
                    apartment = "sympl",
                    first_name = "dumy",
                    last_name = "dumy",
                    street = "dumy",
                    building = "dumy",
                    phone_number = "+201125773493",
                    city = "dumy",
                    country = "EG",
                    email = "dumy@dumy.com",
                    floor = "dumy",
                    state = "dumy"
                ),
                customer = Customer(
                    first_name = "dumy",
                    last_name = "dumy",
                    email = "dumy@dsumy.com",
                    extras = mapOf("re" to "22")
                ),
                extras = mapOf("ee" to 22)
            )
        ).client_secret
        emit(ClientSecretResult.Success(clientSecret))

    }.catch { e -> emit(ClientSecretResult.Error(e.message ?: "Unknown error")) }
}