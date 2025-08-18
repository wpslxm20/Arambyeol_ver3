package com.arambyeol.domain.entity

data class Meal(
    val date: String,
    val menusByMealType: Map<MealType, List<Menu>>
)

data class Menu(
    val menuId: Int,
    val menuName: String,
    val mealType: String,
    val course: String,
    val imgPath: String,
    val averageScore: Double,
    val reviewCount: Int
)

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER
}