package com.arambyeol.domain.usecase

import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.repository.MealRepository

class GetMealsByDateUseCase(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(date: String): Meal = mealRepository.getMealsByDate(date)
}