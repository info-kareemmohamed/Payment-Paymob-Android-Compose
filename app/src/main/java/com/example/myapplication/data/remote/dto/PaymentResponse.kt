package com.example.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    val payment_keys: List<PaymentKeys>,
    val id: String,
    val intention_detail: IntentionDetail,
    val client_secret: String,
    val payment_methods: List<PaymentMethod>,
    val special_reference: String?,
    val extras: Map<String, Any>,
    val confirmed: Boolean,
    val status: String,
    val created: String,
    val card_detail: String?,
    val card_tokens: List<String>,
    @SerializedName("object")
    val type: String
)