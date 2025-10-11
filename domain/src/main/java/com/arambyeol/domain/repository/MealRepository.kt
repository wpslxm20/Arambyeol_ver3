package com.arambyeol.domain.repository

import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.common.Result

interface MealRepository {
    suspend fun getMealsByDate(date: String): Result<Meal>
}