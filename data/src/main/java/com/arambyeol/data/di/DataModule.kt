package com.arambyeol.data.di

import com.arambyeol.data.repository.MealRepositoryImpl
import com.arambyeol.domain.repository.MealRepository
import com.arambyeol.domain.usecase.GetMealsByDateUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    // Repository 구현 바인딩
    @Binds
    abstract fun bindMealRepository(
        impl: MealRepositoryImpl
    ): MealRepository
}