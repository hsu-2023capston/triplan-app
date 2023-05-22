package com.capstone.triplan.di

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.repositoryImpl.GroupRepositoryImpl
import com.capstone.domain.repository.GroupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGroupRepository(
        api: GroupDataSource
    ): GroupRepository {
        return GroupRepositoryImpl(api)
    }

}