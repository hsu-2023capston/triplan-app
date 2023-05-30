package com.capstone.triplan.di

import com.capstone.data.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        prefs: Prefs
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://210.119.104.148:12345")
            .client(provideOkHttpClient(prefs))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(prefs: Prefs): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.SECONDS)
            .readTimeout(5,TimeUnit.SECONDS)
            .build()
    }
}