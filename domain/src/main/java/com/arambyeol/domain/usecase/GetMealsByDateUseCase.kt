package com.arambyeol.domain.usecase

import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.repository.MealRepository
import com.arambyeol.domain.common.Result

class GetMealsByDateUseCase(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(date: String): Result<Meal> = mealRepository.getMealsByDate(date)
}