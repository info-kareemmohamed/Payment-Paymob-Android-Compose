package com.example.myapplication.data.remote

import com.example.myapplication.data.Constants.SECRET_KEY
import com.example.myapplication.data.remote.dto.PaymentResponse
import com.example.myapplication.data.remote.dto.PaymentRequest
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST


interface PaymentService {

    @Headers("Authorization: Token $SECRET_KEY")
    @POST("v1/intention/")
    suspend fun getClientSecret(@Body paymentRequest: PaymentRequest): PaymentResponse
}
