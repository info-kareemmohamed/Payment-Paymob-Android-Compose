package com.example.myapplication.data.remote

import com.example.myapplication.BuildConfig.Secret_Key
import com.example.myapplication.data.remote.dto.PaymentRequest
import com.example.myapplication.data.remote.dto.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface PaymentService {

    @Headers("Authorization: Token $Secret_Key")
    @POST("v1/intention/")
    suspend fun getClientSecret(@Body paymentRequest: PaymentRequest): PaymentResponse
}
