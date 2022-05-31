package com.example.testrainbowsmile.di

import com.example.testrainbowsmile.repository.OrderRepository
import com.example.testrainbowsmile.repository.OrderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}