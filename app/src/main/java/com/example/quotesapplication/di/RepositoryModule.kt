package com.example.quotesapplication.di

import com.example.quotesapplication.data.repository.Repository
import com.example.quotesapplication.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getRepository(repositoryImpl: RepositoryImpl): Repository
}