package com.arambyeol.todaymeal

import com.arambyeol.domain.entity.Meal

sealed class TodayMealUIState {
    data object Loading : TodayMealUIState()
    data class Success(val meal: Meal) : TodayMealUIState()
    data class Error(val message: MealError) : TodayMealUIState()
    data object Empty : TodayMealUIState()
}

sealed class MealError {
    data object Network : MealError()
    data object NotFound : MealError()
    data object Server : MealError()
    data object Timeout : MealError()
    data object Unknown : MealError()
}
