package com.capstone.triplan.di

import com.capstone.data.remote.dataSource.GroupDataSource
import com.capstone.data.remote.dataSource.TimeTableDataSource
import com.capstone.data.remote.dataSource.UserDataSource
import com.capstone.data.repositoryImpl.GroupRepositoryImpl
import com.capstone.data.repositoryImpl.UserRepositoryImpl
import com.capstone.domain.repository.GroupRepository
import com.capstone.domain.repository.UserRepository
import com.capstone.data.remote.dataSource.TripDataSource
import com.capstone.data.repositoryImpl.TimeTableRepositoryImpl
import com.capstone.data.repositoryImpl.TripRepositoryImpl
import com.capstone.domain.repository.TimeTableRepository
import com.capstone.domain.repository.TripRepository
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
    @Provides
    @Singleton
    fun provideTripRepository(
        api: TripDataSource
    ): TripRepository{
        return TripRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserDataSource
    ): UserRepository{
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTimeTableRepository(
        api: TimeTableDataSource
    ): TimeTableRepository {
        return TimeTableRepositoryImpl(api)
    }

}