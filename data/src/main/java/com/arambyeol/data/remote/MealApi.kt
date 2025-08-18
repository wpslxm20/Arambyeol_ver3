package com.arambyeol.data.remote

import com.arambyeol.data.dto.MealDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("/plans/{date}")
    suspend fun getMealsByDate(
        @Query("date") date: String
    ): MealDto
}