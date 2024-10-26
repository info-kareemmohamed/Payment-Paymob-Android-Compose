package com.example.myapplication.domain.repository

import com.example.myapplication.domain.ClientSecretResult
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
     fun getClientSecret(amount: Int, currency: String ="EGP"): Flow<ClientSecretResult<String>>
}