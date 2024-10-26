package com.example.myapplication.di

import com.example.myapplication.data.Constants.BASE_URL
import com.example.myapplication.data.remote.PaymentService
import com.example.myapplication.data.repository.PaymentRepositoryImpl
import com.example.myapplication.domain.repository.PaymentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePaymentService(): PaymentService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaymentService::class.java)
    }

    @Singleton
    @Provides
    fun providePaymentRepository(api: PaymentService): PaymentRepository {
        return PaymentRepositoryImpl(api)
    }



}