package com.arambyeol.data.di

import com.arambyeol.domain.repository.MealRepository
import com.arambyeol.domain.usecase.GetMealsByDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetMealsByDateUseCase(
        mealRepository: MealRepository
    ): GetMealsByDateUseCase = GetMealsByDateUseCase(mealRepository)
}