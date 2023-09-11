package com.raian.imagesearchapp.ui.theme.di

import com.raian.imagesearchapp.ui.theme.Constant
import com.raian.imagesearchapp.ui.theme.components.MainRepository
import com.raian.imagesearchapp.ui.theme.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Singleton
    @Provides
    fun provideApiService():ApiInterface{
        return Retrofit.Builder().baseUrl(Constant.base_url)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)
    }

    @Provides
    fun provideMainRepository(apiInterface: ApiInterface):MainRepository{
        return MainRepository(apiResponse = apiInterface)
    }
}