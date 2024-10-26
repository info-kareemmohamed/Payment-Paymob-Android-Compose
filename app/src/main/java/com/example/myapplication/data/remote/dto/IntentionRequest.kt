package com.example.myapplication.data.remote.dto

data class Item(
    val name: String,
    val amount: Int,
    val description: String,
    val quantity: Int
)

data class BillingData(
    val apartment: String,
    val first_name: String,
    val last_name: String,
    val street: String,
    val building: String,
    val phone_number: String,
    val city: String,
    val country: String,
    val email: String,
    val floor: String,
    val state: String
)

data class Customer(
    val first_name: String,
    val last_name: String,
    val email: String,
    val extras: Map<String, String>
)

data class PaymentRequest(
    val amount: Int,
    val currency: String,
    val payment_methods: List<Int>,
    val items: List<Item>,
    val billing_data: BillingData,
    val customer: Customer,
    val extras: Map<String, Int>
)


data class PaymentKeys(
    val integration: Int,
    val key: String,
    val gateway_type: String,
    val iframe_id: String?
)

data class IntentionDetail(
    val amount: Int,
    val currency: String,
    val items: List<Item>,
    val billing_data: BillingData
)

data class PaymentMethod(
    val integration_id: Int,
    val alias: String?,
    val name: String?,
    val method_type: String,
    val currency: String,
    val live: Boolean,
    val use_cvc_with_moto: Boolean
)

