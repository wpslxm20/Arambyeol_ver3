package com.arambyeol.data.dto

import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.entity.MealType
import com.arambyeol.domain.entity.Menu

// data/dto/MenuDto.kt
data class MenuDto(
    val menuId: Int,
    val menuName: String,
    val mealType: String,
    val course: String,
    val imgPath: String,
    val averageScore: Double,
    val reviewCount: Int
) {
    fun toEntity(): Menu {
        return Menu(
            menuId = menuId,
            menuName = menuName,
            mealType = MealType.valueOf(mealType).toString(),
            course = course,
            imgPath = imgPath,
            averageScore = averageScore,
            reviewCount = reviewCount
        )
    }
}

// data/dto/MenuPlanDto.kt
data class MealDto(
    val date: String,
    val menusByMealType: Map<String, List<MenuDto>>
) {
    fun toEntity(): Meal {
        return Meal(
            date = date,
            menusByMealType = menusByMealType.mapKeys { MealType.valueOf(it.key) }
                .mapValues { entry -> entry.value.map { it.toEntity() } }
        )
    }
}
