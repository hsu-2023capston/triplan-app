package com.capstone.triplan.di

import com.capstone.domain.repository.GroupRepository
import com.capstone.domain.usecase.GroupUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGroupUseCase(repository: GroupRepository): GroupUseCase{
        return GroupUseCase(repository)
    }
}