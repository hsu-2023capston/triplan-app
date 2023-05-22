package com.capstone.triplan.di

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dataSourceImpl.GroupDataSourceImpl
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
}