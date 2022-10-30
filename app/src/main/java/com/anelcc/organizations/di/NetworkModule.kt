package com.anelcc.organizations.di

import com.anelcc.organizations.*
import com.anelcc.organizations.core.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEB_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideRepository(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



    private val TAG = NetworkModule::class.simpleName
}