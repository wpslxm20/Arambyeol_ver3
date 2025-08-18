package com.arambyeol.domain.repository

import com.arambyeol.domain.entity.Meal

interface MealRepository {
    suspend fun getMealsByDate(date: String): Meal
}