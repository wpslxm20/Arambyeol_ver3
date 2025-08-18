package com.arambyeol.data.repository

import com.arambyeol.data.remote.MealApi
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
): MealRepository {
    override suspend fun getMealsByDate(date: String): Meal {
        return mealApi.getMealsByDate(date).toEntity()
    }
}