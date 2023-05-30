package com.capstone.triplan.di

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dataSource.UserDataSource
import com.capstone.data.remote.dataSourceImpl.GroupDataSourceImpl
import com.capstone.data.remote.dataSourceImpl.UserDataSourceImpl
import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.remote.dataSourceImpl.TripDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideGroupDataSource(
        retrofit: Retrofit
    ): GroupDataSource {
        return GroupDataSourceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(
        retrofit: Retrofit
    ): UserDataSource {
        return UserDataSourceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideTripDataSource(
        retrofit: Retrofit
    ): TripDataSource {
        return TripDataSourceImpl(retrofit)
    }
}