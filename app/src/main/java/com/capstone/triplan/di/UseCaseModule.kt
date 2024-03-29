package com.capstone.triplan.di

import com.capstone.domain.repository.GroupRepository
import com.capstone.domain.repository.MemoRepository
import com.capstone.domain.repository.TimeTableRepository
import com.capstone.domain.repository.UserRepository
import com.capstone.domain.usecase.GroupUseCase
import com.capstone.domain.usecase.UserUseCase
import com.capstone.domain.repository.TripRepository
import com.capstone.domain.usecase.MemoUseCase
import com.capstone.domain.usecase.TimeTableUseCase
import com.capstone.domain.usecase.TripUseCase
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
    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository) : UserUseCase {
        return UserUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideTripUseCase(repository: TripRepository): TripUseCase{
        return TripUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTimeTableUseCase(repository: TimeTableRepository): TimeTableUseCase {
        return TimeTableUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMemoUseCase(repository: MemoRepository): MemoUseCase{
        return MemoUseCase(repository)
    }
}